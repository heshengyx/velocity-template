var _myDialog;
function showDialog(url, options) {
	_myDialog = dialog(options);// 初始化一个带有loading图标的空对话框
	jQuery.ajax({
		url : url,
		success : function(data) {
			_myDialog.content(data).showModal();// 填充对话框内容
		}
	});
}