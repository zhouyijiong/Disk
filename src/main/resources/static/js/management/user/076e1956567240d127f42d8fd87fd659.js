window.onload = function () {
    /*	queryCategory('all');
        getCapacityInfo();*/
}

function getCapacityInfo() {
    $.ajax({
        type: 'POST',
        url: '/file/getCapacityInfo',
        dataType: 'json',
        contentType: false,
        processData: false,
        success: function (data) {
            if (data.error) {
                alert(data.error);
                logout();
            } else {
                var pbc = document.getElementById('progressBarInfoCapacity').innerHTML =
                    fileSizeFormat(data.useCapacityValue) + "/" + fileSizeFormat(data.totalCapacity);
                var obj = document.getElementById('progressDown').style.width = data.useCapacityPercentage + '%';
            }
        }
    });
}

function topSetChange(obj) {
    let index = obj.selectedIndex;
    if (index > 0) {
        if (index === 1) {
            logout();
        } else {
            changeMode();
        }
    }
}

function fileUpload(obj) {
    obj.disabled = true;
    var time = 0;
    var fileUploadBut = document.getElementById('fileUpload-but');
    var key = true;
    var hander = setInterval(function () {
        if (key) {
            fileUploadBut.innerHTML = time++;
        } else {
            clearInterval(hander);
            fileUploadBut.innerHTML = '上传文件';
            obj.disabled = false;
        }
    }, 1000);
    var fileCount;
    var fileSize;
    var totalFileSize;
    $.ajax({
        type: 'POST',
        url: '/file/getUploadInfo',
        dataType: 'json',
        contentType: false,
        processData: false,
        async: false,
        success: function (data) {
            if (data.error) {
                alert(data.error);
                logout();
            } else {
                fileCount = data.fileCount;
                fileSize = data.fileSize;
                totalFileSize = data.totalFileSize;
            }
        }
    });
    var files = document.getElementById("fileUpload-input").files;
    if (files.length == 0) {
        key = false;
        return;
    }
    if (files.length > fileCount) {
        alert('上传的文件数量超过: ' + fileCount);
        key = false;
        return;
    }
    var sizes = 0;
    files = [...files];
    for (i = 0; i < files.length; i++) {
        if (files[i].size == 0) {
            files.splice(i, 1);
            if (files.length == 0) {
                alert('单个文件大小不能为0');
                key = false;
                return;
            }
        }
        if (files[i].size > fileSize) {
            files.splice(i, 1);
            if (files.length == 0) {
                alert('单个文件大小超过: ' + fileSizeFormat(fileSize));
                key = false;
                return;
            }
        }
        sizes += files[i].size;
        if (sizes > totalFileSize) {
            alert('上传的总文件大小超过: ' + fileSizeFormat(totalFileSize));
            key = false;
            return;
        }
    }
    var formData = new FormData();
    for (item of files) {
        formData.append('myfile', item, item.name);
    }
    $.ajax({
        type: 'POST',
        url: '/file/fileUpload',
        dataType: 'json',
        contentType: false,
        processData: false,
        data: formData,
        success: function (data) {
            key = false;
            queryCategory(getKey());
            getCapacityInfo();
            if (data.error) {
                alert(data.error);
                logout();
                return;
            }
            if (data.exception) {
                alert(data.exception);
                return;
            }
            if (data.message) {
                alert(data.message);
                return;
            }
            alert('文件上传成功!');
        }
    });
}

function deleteTr() {
    var trs = document.getElementsByTagName('tr');
    for (var i = 1; i < trs.length; i++) {
        trs[i].closest('tr').remove();
        i--;
    }
}

function appendTr(data, tag) {
    var list = data.list;
    var table = document.getElementById('list');
    for (var i = 0; i < data.size; i++) {
        var file = list[i];
        var tr = document.createElement('tr');
        tr.className = 'after-tr selected';
        var td1 = document.createElement('td');
        td1.class = 'td1';
        var td1_input = document.createElement('input');
        td1_input.type = 'checkbox';
        td1.className = 'td1';
        td1.appendChild(td1_input);
        var td2 = document.createElement('td');
        td2.className = 'td2';
        var td2_input = document.createElement('input');
        td2_input.className = 'td2-input';
        td2_input.value = file.fileName;
        td2_input.onlyId = file.fileHash;
        td2_input.fileSize = file.fileSize;
        switch (tag) {
            case 'share':
                td2_input.setAttribute('disabled', false);
                break;
            case 'favorites':
                td2_input.setAttribute('disabled', false);
                break;
            default:
                if (file.shareLink) {
                    td2_input.style.color = 'rgba(0,0,255,0.6)';
                } else if (file.isShare == 1) {
                    td2_input.style.color = 'rgba(100,0,100,0.6)';
                }
        }
        td2.appendChild(td2_input);
        var td3 = document.createElement('td');
        td3.className = 'td3';
        td3.innerHTML = fileSizeFormat(file.fileSize);
        var td4 = document.createElement('td');
        td4.className = 'td4';
        td4.innerHTML = dateFormat(file.uploadTime);
        var td5 = document.createElement('td');
        td5.className = 'td5';
        td5.innerHTML = dateFormat(file.lastEditTime);
        var td6 = document.createElement('td');
        td6.className = 'td6';
        td6.innerHTML = '预览';
        var td7 = document.createElement('td');
        td7.className = 'td7';
        var td7_select = document.createElement('select');
        td7_select.setAttribute("onchange", "selectedOperting(this)");
        var td7_option1 = document.createElement('option');
        td7_option1.innerHTML = '==未选择==';
        td7_select.appendChild(td7_option1);
        var td7_option2 = document.createElement('option');
        td7_option2.innerHTML = '下载文件';
        td7_select.appendChild(td7_option2);
        switch (tag) {
            case 'share':
                var td7_option1 = document.createElement('option');
                td7_option1.innerHTML = '收藏文件';
                td7_select.appendChild(td7_option1);
                break;
            case 'favorites':
                var td7_option1 = document.createElement('option');
                td7_option1.innerHTML = '取消收藏';
                td7_select.appendChild(td7_option1);
                break;
            default:
                if (file.isShare == 0) {
                    var td7_option1 = document.createElement('option');
                    td7_option1.innerHTML = '分享文件';
                    td7_select.appendChild(td7_option1);
                } else {
                    var td7_option4 = document.createElement('option');
                    td7_option4.innerHTML = '取消分享';
                    td7_select.appendChild(td7_option4);
                }
                if (file.shareLink == null) {
                    var td7_option2 = document.createElement('option');
                    td7_option2.innerHTML = '分享链接';
                    td7_select.appendChild(td7_option2);
                } else {
                    var td7_option5 = document.createElement('option');
                    td7_option5.innerHTML = '查看链接';
                    td7_select.appendChild(td7_option5);
                    var td7_option6 = document.createElement('option');
                    td7_option6.innerHTML = '取消链接';
                    td7_select.appendChild(td7_option6);
                }
                var td7_option3 = document.createElement('option');
                td7_option3.innerHTML = '删除文件';
                td7_select.appendChild(td7_option3);
        }
        td7.appendChild(td7_select);
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);
        tr.appendChild(td7);
        table.appendChild(tr);
    }
}

function selectedOperting(obj) {
    var tr = obj.parentNode.parentNode;
    var td = tr.getElementsByTagName('td')[1];
    var input = td.getElementsByTagName('input')[0];
    var onlyId = input.onlyId;
    var fileSize = input.fileSize;
    var fileName = input.value;
    switch (obj[obj.selectedIndex].value) {
        case '==未选择==':
            break;
        case '下载文件':
            downloadFile(onlyId, fileSize);
            break;
        case '分享文件':
            shareFile(onlyId, fileSize);
            break;
        case '取消分享':
            cancelShare(onlyId, fileSize);
            break;
        case '分享链接':
            shareLink(fileName, onlyId, fileSize);
            break;
        case '查看链接':
            showLink(onlyId, fileSize);
            break;
        case '取消链接':
            cancelLink(onlyId, fileSize);
            break;
        case '删除文件':
            deleteFile(onlyId, fileSize);
            break;
        case '收藏文件':
            favoritesFile(onlyId, fileSize);
            break;
        case '取消收藏':
            cancelFavorite(onlyId, fileSize);
            break;
        default:
            alert('新手做的系统不容易,大佬破解手下留情');
    }
}

function downloadFile(onlyId, fileSize) {
    window.location.href = "/file/fileDownload/" + fileSize + "/" + onlyId;
}

function shareFile(onlyId, fileSize) {
    $.ajax({
        type: 'POST',
        url: '/file/shareFile',
        dataType: 'json',
        data: {'onlyId': onlyId, 'fileSize': fileSize},
        success: function (data) {
            if (data.error) {
                alert(data.error);
                logout();
            } else {
                alert('分享成功');
                queryCategory(getKey());
            }
        }
    });
}

function cancelShare(onlyId, fileSize) {
    $.ajax({
        type: 'POST',
        url: '/file/cancelShare',
        dataType: 'json',
        data: {'onlyId': onlyId, 'fileSize': fileSize},
        success: function (data) {
            if (data.error) {
                alert(data.error);
                logout();
            } else {
                alert('取消成功');
                queryCategory(getKey());
            }
        }
    });
}

function shareLink(fileName, onlyId, fileSize) {
    sessionStorage.setItem('onlyId', onlyId);
    sessionStorage.setItem('fileName', fileName);
    sessionStorage.setItem('fileSize', fileSize);
    window.open('sendsharelink');
}

function showLink(onlyId, fileSize) {
    $.ajax({
        type: 'POST',
        url: '/file/viewLink',
        dataType: 'json',
        data: {'onlyId': onlyId, 'fileSize': fileSize},
        success: function (data) {
            if (data.error) {
                alert(data.error);
                logout();
            } else {
                alert(data.linkInfo);
            }
        }
    });
}

function cancelLink(onlyId, fileSize) {
    $.ajax({
        type: 'POST',
        url: '/file/cancelLink',
        dataType: 'json',
        data: {'onlyId': onlyId, 'fileSize': fileSize},
        success: function (data) {
            if (data.error) {
                alert(data.error);
                logout();
            } else {
                alert('取消成功');
                queryCategory(getKey());
            }
        }
    });
}

function deleteFile(onlyId, fileSize) {
    $.ajax({
        type: 'POST',
        url: '/file/delete',
        dataType: 'json',
        data: {'onlyId': onlyId, 'fileSize': fileSize},
        success: function (data) {
            if (data.error) {
                alert(data.error);
                logout();
            } else {
                alert('删除成功');
                queryCategory(getKey());
                getCapacityInfo();
            }
        }
    });
}

function favoritesFile(onlyId, fileSize) {
    $.ajax({
        type: 'POST',
        url: '/file/favorites',
        dataType: 'json',
        data: {'onlyId': onlyId, 'fileSize': fileSize},
        success: function (data) {
            if (data.error) {
                alert(data.error);
                logout();
            } else {
                alert('收藏成功');
            }
        }
    });
}

function cancelFavorite(onlyId, fileSize) {
    $.ajax({
        type: 'POST',
        url: '/file/cancelFavorite',
        dataType: 'json',
        data: {'onlyId': onlyId, 'fileSize': fileSize},
        success: function (data) {
            if (data.error) {
                alert(data.error);
                logout();
            } else {
                alert('取消成功');
                queryCategory('favorites');
            }
        }
    });
}

function queryFileByName(page) {
    var key = getKey();
    $.ajax({
        type: 'POST',
        url: '/file/queryFileName',
        dataType: 'json',
        data: {'category': key, 'fileName': document.getElementById('fileSearch-input').value, 'page': page ? page : 0},
        success: function (data) {
            if (data.error) {
                alert(data.error);
                logout();
            } else {
                showQueryResult(data, key);
            }
        }
    });
}

function queryFileByCategory(category) {
    var key = 'all';
    var divs = category.parentNode.getElementsByTagName('div');
    for (var item of divs) {
        var state = item.getAttribute('data-state');
        if (state == 1) {
            item.setAttribute('data-state', 0);
            item.style.background = '';
        }
        if (item == category) {
            if (state == 0) {
                category.setAttribute('data-state', 1);
                category.style.background = 'rgba(150,150,150,0.2)';
                key = category.id;
            }
        }
    }
    queryCategory(key);
}

function queryCategory(key, page) {
    $.ajax({
        type: 'POST',
        url: '/file/queryCategory',
        dataType: 'json',
        data: {'category': key, 'page': page ? page : 0},
        success: function (data) {
            if (data.error) {
                alert(data.error);
                logout();
            } else {
                showQueryResult(data, key);
            }
        }
    });
}

function buy() {
    window.open('buyService');
}

function pageChange(num) {
    var pageObj = document.getElementById('page');
    var point = pageObj.innerHTML.indexOf('/');
    var current = pageObj.innerHTML.substring(0, point);
    var totalPage = pageObj.innerHTML.substring(point + 1);
    current = parseInt(current) + num;
    if (current < 1) {
        current = 1;
    } else if (current > totalPage) {
        current = totalPage;
    }
    pageObj.innerHTML = current + '/' + totalPage;
    queryCategory(getKey(), current - 1);
}

function loadPage(page) {
    document.getElementById('page').innerHTML = '1/' + page;
}

function showQueryResult(data, key) {
    deleteTr();
    if (data.size > 0) {
        appendTr(data, key);
        loadPage(data.page);
    } else {
        loadPage(1);
    }
}

function changeMode() {
    var html = document.getElementById('html');
    if (html.getAttribute('data-mode') == 'morning') {
        html.setAttribute('data-mode', 'night');
    } else {
        html.setAttribute('data-mode', 'morning');
    }
}

function getKey() {
    var divs = document.getElementById('after-left').getElementsByTagName('div');
    for (var item of divs) {
        if (item.getAttribute('data-state') == 1) {
            return item.id;
        }
    }
    return 'all';
}

function dateFormat(time) {
    var date = new Date(time);
    return date.getFullYear() + '.' + (date.getMonth() + 1) + '.' + date.getDate();
}

$('#fileSearch').bind('keyup', function (event) {
    if (event.keyCode == '13') {
        var pageObj = document.getElementById('page');
        queryFileByName(pageObj.innerHTML.substring(0, pageObj.innerHTML.indexOf('/')) - 1);
    }
});