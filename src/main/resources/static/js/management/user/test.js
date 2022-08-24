window.onload = function () {
    getFileCategoryList();
}

function operateCategory() {
    let fileCategoryList = document.getElementById('fileCategoryList').rows;
    let data = [];
    for (let i = 0; i < fileCategoryList.length; ++i) {
        let item = fileCategoryList[i].cells[0];
        if (item.getAttribute('checked') === '1') data.push(item.innerHTML);
    }
    if (data.length > 0) {
        alert('删除');
    } else {
        let category = prompt('请输入文件类别:');
        if (!category) return;
        ajax.post(
            '/fileCategory/addFileCategory',
            {'category': category},
            () => {
                getFileCategoryList();
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
        td.innerHTML = data[index];
        tr.append(td);
        thead.append(tr);
    }
}

function delElement(tableId, theadId) {
    document.getElementById(theadId).remove();
    let thead = document.createElement('thead');
    thead.setAttribute('id', theadId);
    document.getElementById(tableId).append(thead);
}