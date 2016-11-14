
function doFirst(){
	document.getElementById('myFile').onchange = fileChange;
}
function fileChange(){
	var file = document.getElementById('myFile').files[0];
	var message = 'File name: '+ file.name +'\n';
	message += 'File size: '+ file.size +' byte(s)\n';
	message += 'File type: '+ file.type +'\n';
	message += 'Last Modified: '+ file.lastModifiedDate +'\n';

	var readFile = new FileReader();
	readFile.readAsDataURL(file);//讀取文件的網址
	readFile.onload = function(){
		var image = document.getElementById('image');
		image.src = readFile.result;
		image.style.width = '150px';
		image.style.height = '150px';
		console.log(readFile.result);
	};	
}
window.addEventListener('load',doFirst,false);