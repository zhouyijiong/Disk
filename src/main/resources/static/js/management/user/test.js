window.onload = function () {
    getFileCategoryList();
}

function operateCategory() {
    let fileCategoryList = document.getElementById('fileCategoryList').rows;
    let data = [];
    for (let i = 0; i < fileCategoryList.length; ++i) {
        let item = fileCategoryList[i].cells[0];
        if (item.getAttribute('checked') === '1') data.push(item);
    }
    if (data.length > 0) {
        data.forEach(i => i.remove());
    } else {
        let category = prompt('请输入文件类别:');
        if (!category) return;
        ajax.post(
            '/fileCategory/addFileCategory',
            {'category': category},
            (response) => {
                if (response.message == null) getFileCategoryList();
            }
        );
    }
}

function getFileCategoryList() {
    ajax.get(
        '/fileCategory/getFileCategoryList',
        null,
        (response) => {
            delElement('fileCategoryList', 'fileCategoryThead');
            addElement(response.data, 'fileCategoryThead');
        }
    );
}

function topSetChange(obj) {
    let index = obj.selectedIndex;
    if (index === 1) {
        delCookie('identity');
        window.location.href = '/';
    }
}

function addElement(data, theadId) {
    let thead = document.getElementById(theadId);
    for (let index in data) {
        let tr = document.createElement('tr');
        let td = document.createElement('td');
        td.setAttribute('checked', '0');
        td.onclick = () => {
            let val = td.getAttribute('checked');
            if (val === '0') {
                td.setAttribute('checked', '1');
                td.style.background = 'rgba(0,0,0,0.1)';
            } else {
                td.setAttribute('checked', '0');
                td.style.background = '';
            }
        };
        td.innerHTML = data[index];
        tr.append(td);
        thead.append(tr);
    }
}

function fileUpload(cur) {
    cur.disabled = true;
    let time = 0;
    let fileUploadInfo = document.getElementById('fileUploadInfo');
    let key = true;
    let timer = setInterval(() =>{
        if (key) {
            fileUploadInfo.innerHTML = ++time;
        } else {
            clearInterval(timer);
            fileUploadInfo.innerHTML = '上传文件';
            cur.disabled = false;
        }
    }, 1000);
    let files = document.getElementById("fileUploadBut").files;
    console.log(files);
    if (files.length === 0) {
        key = false;
        return;
    }
    let sizes = 0;
    files = [...files];
}

function delElement(tableId, theadId) {
    document.getElementById(theadId).remove();
    let thead = document.createElement('thead');
    thead.setAttribute('id', theadId);
    document.getElementById(tableId).append(thead);
}