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
        let fileCategory = prompt('请输入文件类别:');
        if (!fileCategory) return;
        ajax.post('/fileCategory/addFileCategory', {'fileCategoryHash':md5(fileCategory)}, function (response) {
            console.log(response);
        });
        console.log(fileCategory);
    }
}