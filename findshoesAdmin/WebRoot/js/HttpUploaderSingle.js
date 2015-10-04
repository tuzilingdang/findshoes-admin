/*
版权所有 2009-2014 荆门泽优软件有限公司
保留所有权利
官方网站：http://www.ncmem.com
官方博客：http://www.cnblogs.com/xproer
产品首页：http://www.ncmem.com/webplug/http-uploader2/index.asp
在线演示：http://www.ncmem.com/products/http-uploader/demo2/index.html
开发文档：http://www.cnblogs.com/xproer/archive/2011/03/15/1984950.html
升级日志：http://www.cnblogs.com/xproer/archive/2011/03/15/1985091.html
示例下载：http://yunpan.cn/lk/sVMbJWKR2gfwm?sid=301
文档下载：http://yunpan.cn/lk/sVMbQ2a8qSMTa?sid=301
联系邮箱：1085617561@qq.com
联系QQ：1085617561
更新记录：
	2014-02-28 使用JQuery优化代码并增强兼容性。
*/

/*
单文件上传控件
更新记录：
	2012-2-9 创建
*/
function HttpUploaderSingleMgr()
{
	//http://www.ncmem.com/
	this.Domain = "http://" + document.location.host;
	
	this.Config = {
		"EncodeType"		: "GB2312"//UTF-8,GB2312
		, "Version"			: "2,5,37,20873"
		, "Company"			: "荆门泽优软件有限公司"
		, "License"			: ""
		, "FileFilter"		: "*"//文件类型。所有类型：*。自定义类型：jpg,png,gif,bmp
		, "AllowMultiSelect": 1//多选开关。1:开启多选。2:关闭多选
		, "InitDir"			: ""//初始路径。示例：D:\\Soft
		, "AppPath"			: ""//网站虚拟目录名称。子文件夹 web
		, "PostUrl"			: "http://localhost:1890/asp.net/upload.aspx"
		, "CabPath"			: "http://www.ncmem.com/products/http-uploader2/HttpUploader/HttpUploader.cab"
		, "ClsidDroper"		: "4D2454F8-EB25-465f-B867-C2A3E9F3D4B4"
		, "ClsidUploader"	: "7AAE6FD3-C2F2-49d5-A790-1103848B3531"
		, "ClsidPartition"	: "6F3EB4AF-FC9C-4570-A686-88B4B427C6FE"
		//64bit
		, "CabPath64"		: "http://www.ncmem.com/products/http-uploader2/HttpUploader/HttpUploader64.cab"
		, "ClsidDroper64"	: "C9388115-887C-4d64-B175-F8F1AA5437BF"
		, "ClsidUploader64"	: "E95E03B2-8718-4871-B965-A9D21108DCD2"
		, "ClsidPartition64": "3AFFCB6D-55ED-4ada-A1EC-D7D87BA29E51"
	};

	this.ActiveX = {
		  "Droper"		: "Xproer.HttpDroper2"
		, "Uploader"	: "Xproer.HttpUploader2"
		, "Partition"	: "Xproer.HttpPartition2"
		//64bit
		, "Droper64"	: "Xproer.HttpDroper2x64"
		, "Uploader64"	: "Xproer.HttpUploader2x64"
		, "Partition64"	: "Xproer.HttpPartition2x64"
	};
	
	//附加参数
	this.Fields = {
		 "uname": "test"
		,"upass": "test"
	};
	
	var browserName = navigator.userAgent.toLowerCase();
	this.ie = browserName.indexOf("msie") > 0;
	//IE11
	this.ie = this.ie ? this.ie : browserName.search(/(msie\s|trident.*rv:)([\w.]+)/)!=-1;
	
	this.CheckVersion = function()
	{
		//Win64
		if (window.navigator.platform == "Win64")
		{
			this.Config["CabPath"]			= this.Config["CabPath64"];
			this.Config["ClsidDroper"]		= this.Config["ClsidDroper64"];
			this.Config["ClsidUploader"]	= this.Config["ClsidUploader64"];
			this.Config["ClsidPartition"]	= this.Config["ClsidPartition64"];
			//
			this.ActiveX["Uploader"]		= this.ActiveX["Uploader64"];
			this.ActiveX["Partition"]		= this.ActiveX["Partition64"];
		}
	};
	this.CheckVersion();

	//初始化路径
	this.InitPath = function()
	{
		this.Config["CabPath"] = this.Domain + this.Config["AppPath"] + this.Config["CabPath"];
		this.Config["PostUrl"] = this.Domain + this.Config["AppPath"] + this.Config["PostUrl"];
	};

	//在外部调用。
	this.Load = function()
	{
		//this.InitPath();

		//自动安装CAB
		var acx = '<div style="display:none">';
		//文件上传控件
		acx += '<object id="objHttpUpLoader" classid="clsid:' + this.Config["ClsidUploader"] + '"';
		acx += ' codebase="' + this.Config["CabPath"] + '#version='+this.Config["Version"]+'" width="1" height="1" ></object>';
		acx += '</div>';
		//上传列表项模板
		acx += '<div id="UpPanel">';
		acx += '<a id="UpBtnFileSel" class="btn">选择文件</a>';
		acx += '<a id="UpMsg" class="msg"></a>';
		acx += '<a id="UpBtnStop" class="btn">停止</a>';
		acx += '</div>';
		document.write(acx);
	};

	//初始化，一般在window.onload中调用
	this.Init = function ()
	{
		var mgr = this;

		//添加单个文件
		this.BtnFile = $("#UpBtnFileSel");
		this.BtnFile.click(function () { mgr.OpenFileDialog(); });
		//停止上传
		this.BtnStop = $("#UpBtnStop");
		this.BtnStop.click(function () { mgr.Stop(); });
		//消息
		this.Msg = $("#UpMsg");

		//创建控件
		this.CreateATL();
	};
	
	//创建控件
	this.CreateATL = function()
	{
		//创建控件
		this.State = HttpUploaderState.None;
		this.ATL = new ActiveXObject(this.ActiveX["Uploader"]);
		this.ATL.Object = this;
		this.ATL.License = this.Config["License"];
		this.ATL.PostUrl = this.Config["PostUrl"];
		this.ATL.EncodeType = this.Config["EncodeType"];
		this.ATL.CompanyLicensed = this.Config["Company"];
		this.ATL.OnPost = HttpUploader_Process;
		this.ATL.OnStateChanged = HttpUploader_StateChanged;
		this.ATL.FileID = 0;
		this.LocalFile = "";
		this.FileID = 0;
	};

	//初始化附加字段，每次选择文件时调用
	this.InitFields = function()
	{
		//添加附加信息
		this.ATL.ClearFields(); //清空附加字段
		for (var field in this.Fields)
		{
			this.ATL.AddField(field, this.Fields[field]);
		}
	};

	//上传
	this.Upload = function(filePath)
	{
		this.Msg.css("display", "inline" );
		this.Msg.text( "开始连接服务器" );
		this.ATL.LocalFile = filePath;
		this.BtnStop.css("display", "");
		this.BtnStop.text( "停止" );
		this.BtnFile.css("disabled",true);//禁用文件选择按钮
		this.State = HttpUploaderState.Posting;
		this.InitFields();

		this.ATL.Post();
	};

	/*
		上传完成时的回调函数
		参数：
			msg 服务器返回的消息
	*/
	this.CompleteHook = function(msg) { };

	//检查文件
	this.CheckFile = function()
	{
		this.ATL.CheckFile();
	};

	//方法-停止传输
	this.Stop = function()
	{
		this.BtnFile.css("disabled", false); //启用文件选择按钮
		this.BtnStop.css("display", "none");
		this.Msg.text( "传输已停止....");
		this.ATL.Stop();
		this.State = HttpUploaderState.Stop;
	};

	//打开文件选择对话框
	this.OpenFileDialog = function()
	{
		var obj = new ActiveXObject(this.ActiveX["Partition"]);
		obj.FileFilter = this.Config["FileFilter"];
		obj.AllowMultiSelect = this.Config["AllowMultiSelect"];
		obj.InitDir = this.Config["InitDir"];
		if (!obj.ShowDialog()) return;

		var list = obj.GetSelectedFiles();
		if (list == null) return;
		if (list.lbound(1) == null) return;
		this.Upload(list.getItem(0));
	};
}

var HttpUploaderErrorCode = {
	"0": "连接服务器错误"
	, "1": "发送数据错误"
	, "2": "接收数据错误"
	, "3": "未设置本地文件"
	, "4": "本地文件不存在"
	, "5": "打开本地文件错误"
	, "6": "不能读取本地文件"
	, "7": "公司未授权"
	, "8": "未设置IP"
	, "9": "域名未授权"
	//md5
	, "200": "无打打开文件"
	, "201": "文件大小为0"
};

var HttpUploaderState = {
	Ready: 0,
	Posting: 1,
	Stop: 2,
	Error: 3,
	GetNewID: 4,
	Complete: 5,
	WaitContinueUpload: 6,
	None: 7,
	Waiting: 8
};

//上传错误
function HttpUploader_Error(obj)
{
	obj.Msg.innerText = HttpUploaderErrorCode[obj.ATL.ErrorCode];
	obj.State = HttpUploaderState.Error;
	obj.BtnFile.disabled = false //启用文件选择按钮
}

//上传完成
function HttpUploader_Complete(obj)
{
	obj.BtnFile.css("disabled", false); //启用文件选择按钮
	obj.BtnStop.css("display", "none");
	//obj.pProcess.style.width = "100%";
	//obj.pPercent.innerText = "100%";
	obj.Msg.text( "上传完成" );
	obj.State = HttpUploaderState.Complete;
	obj.CompleteHook(obj.ATL.Response);
}

//传输进度。频率为每秒调用一次
function HttpUploader_Process(obj, speed, postedLength, percent, time)
{
	//obj.pPercent.innerText = percent;
	//obj.pProcess.style.width = percent;
	var str = "进度::" + postedLength;
	obj.Msg.text( str );
}

//服务器连接成功
function HttpUploader_Connected(obj)
{
	obj.Msg.text( "服务器连接成功" );
}

//MD5计算中
function HttpUploader_MD5_Working(obj)
{
	var msg = "扫描进度：" + obj.ATL.Md5Percent + "%";
	obj.Msg.text( msg );
}

//MD5计算完毕
function HttpUploader_MD5_Complete(obj)
{
	var md5 = obj.ATL.MD5;
	//在此处增加服务器验证代码。
	obj.Msg.text( "MD5计算完毕，开始连接服务器..." );

	obj.Upload();
}

/*
HUS_Leisure			=0	//空闲
,HUS_Uploading		=1	//上传中 
,HUS_Stop  			=2	//停止 
,HUS_UploadComplete	=3	//传输完毕 
,HUS_Error 			=4	//错误 
,HUS_Connected 		=5	//服务器已连接
,HUS_Md5Working		=6	//MD5计算中
,HUS_Md5Complete	=7	//MD5计算完毕
*/
function HttpUploader_StateChanged(obj, state)
{
	switch (state)
	{
		case 0:
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			HttpUploader_Complete(obj);
			break;
		case 4:
			HttpUploader_Error(obj);
			break;
		case 5:
			HttpUploader_Connected(obj);
			break;
		case 6:
			HttpUploader_MD5_Working(obj);
			break;
		case 7:
			HttpUploader_MD5_Complete(obj);
			break;
	}
}