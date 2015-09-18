package com.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.dao.ImageDAO;
import com.dao.OnlineStoreDAO;
import com.dao.ShoesDAO;
import com.model.Image;
import com.model.OnlineStore;
import com.model.Shoes;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class RoleFileAction extends ActionSupport {
	private static final long serialVersionUID = 8940689809494170308L;

	//Excel文件
	private File uploadFile;
	private String uploadFileFileName;
	//图片文件
	private List<File> file;
	private List<String> fileFileName;
	private List<String> fileContentType;
	private ShoesDAO shoesDAO;
	private OnlineStoreDAO onlineDAO;
	private ImageDAO imageDAO;

	public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
	}

	public List<String> getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
	}

	public List<String> getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(List<String> fileContentType) {
		this.fileContentType = fileContentType;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public ShoesDAO getShoesDAO() {
		return shoesDAO;
	}

	public void setShoesDAO(ShoesDAO shoesDAO) {
		this.shoesDAO = shoesDAO;
	}

	@Override
	public String execute() throws Exception {
		
		//判断Excel文件的拓展名
		int uploadFileIndex = uploadFileFileName.lastIndexOf('.');
		String uploadFileExt = uploadFileFileName.substring(uploadFileIndex).toLowerCase();
		boolean isExcel = true;
		if( !".xlsx".equals(uploadFileExt) && !".xls".equals(uploadFileExt)){
			isExcel = false;
		}
		
		//判断图片文件的拓展名
		boolean isImg = true;
		for(int i=0;i<file.size();i++){
			String imgName = fileFileName.get(i);
			int imgIndex = imgName.lastIndexOf('.');
			String imgExt = imgName.substring(imgIndex).toLowerCase();
			if( !".jpg".equals(imgExt) && !".png".equals(imgExt)){
				isImg=false;
				break;
			}
		}
		
		if( isExcel && isImg){
			String directory = "/upload";
			String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory);
			// 生成上传的文件对象
			File target = new File(targetDirectory, uploadFileFileName);
			// 如果文件已经存在，删除原文件
			if (target.exists()) {
				target.delete();
			}
			
			// 复制file对象，实现Excel文件上传
			try {
				FileUtils.copyFile(uploadFile, target);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//解析Excel文件并上传图片文件
			String tip = loadRoleInfo(uploadFileFileName,file,fileFileName);
			if (!"success".equals(tip)) {
				ActionContext.getContext().put("Tip", tip);
			} else {
				ActionContext.getContext().put("Tip", "添加成功!");
			}
		}else{
			ActionContext.getContext().put("Tip", "文件格式错误");
		}		
		return SUCCESS;
	}

	@Override
	public void validate() {
		// TODO Auto-generated method stub
		super.validate();
	}

	/**
	 * 把Excele表读出的数据，组装成一个List,统一导入数据库
	 * 
	 * @param uploadFileFileName
	 */
	public String loadRoleInfo(String uploadFileFileName,List file, List fileName) {

		//上传的Excel文件存储的文件夹
		String directory = "/upload";
		String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory);
		File target = new File(targetDirectory, uploadFileFileName);

		List shoesList = new ArrayList();
		List onLineStoreList = new ArrayList();
		List imgList = new ArrayList();
		shoesDAO = new ShoesDAO();
		onlineDAO = new OnlineStoreDAO();
		imageDAO = new ImageDAO();

		try {
			FileInputStream fi = new FileInputStream(target);
			Workbook wb = null;
			// 兼容Excel不同版本
			try {
				wb = new XSSFWorkbook(fi);
			} catch (Exception e) {
				wb = new HSSFWorkbook(new FileInputStream(target));
			}
			Sheet sheet = wb.getSheetAt(0);
			// 得到Excel的行数
			int rowNum = sheet.getPhysicalNumberOfRows();
			// 得到Excel的列数
			int cellNum = 0;
			if (rowNum >= 1 && sheet.getRow(0) != null) {
				cellNum = sheet.getRow(0).getPhysicalNumberOfCells();
			}

			for (int i = 1; i < rowNum; i++) {
				Shoes shoes = new Shoes();
				OnlineStore onlineStore = new OnlineStore();
				Image image = new Image();
				Row row = sheet.getRow(i);
				String shoesId = "";
				
				for (int j = 0; j < cellNum; j++) {
					Cell cell = row.getCell(j);
					String cellValue = null;
					if (cell != null) {
						switch (cell.getCellType()) { // 判断excel单元格内容的格式，并对其进行转换，以便插入数据库
						case 0:
							if (DateUtil.isCellDateFormatted(cell)) {
								SimpleDateFormat format = new SimpleDateFormat(
										"yyyy/MM/dd");
								cellValue = format.format(DateUtil.getJavaDate(cell.getNumericCellValue())).toString();
							} else
								cellValue = String.valueOf((int) cell
										.getNumericCellValue());
							break;
						case 1:
							cellValue = cell.getStringCellValue();
							break;
						case 2:
							cellValue = String.valueOf(cell.getDateCellValue());
							break;
						case 3:
							cellValue = "";
							break;
						case 4:
							cellValue = String.valueOf(cell.getBooleanCellValue());
							break;
						case 5:
							cellValue = String.valueOf(cell.getErrorCellValue());
							break;
						default:
							break;
						}
					}

				
					// 通过列数来判断对应插如的字段
					switch (j) {
					//鞋子ID
					case 0:
						if (cellValue == null || "".equals(cellValue)) {
							String tip = "添加失败，鞋子ID不能为空";
							return tip;
						} else {
							shoesId = cellValue;
							Shoes s = shoesDAO.get(cellValue);
							if (s != null) {
								String tip = "添加失败，鞋子ID " + cellValue + " 已存在";
								return tip;
							} else {
								String root = ServletActionContext.getServletContext().getRealPath("/" + cellValue);
								File dir = new File(root);
								if (dir.exists() == false)
									dir.mkdirs();
								shoes.setGoodsId(cellValue);
								onlineStore.setGoodsId(cellValue);
								image.setGoodsId(cellValue);
								break;
							}
						}
				   //品牌
					case 1:
						shoes.setBrand(cellValue);
						break;
					//上市时间
					case 2:
					//	DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
					//	Date date = format.parse(cellValue);
					//	shoes.setShowDate(new Timestamp(date.getTime()));
						shoes.setShowDate(cellValue);
						break;
					//季节
					case 3:
						shoes.setSeason(cellValue);
						break;
					//场合
					case 4:
						shoes.setOccasion(cellValue);
						break;
					//跟高
					case 5:
						shoes.setHeelHeight(cellValue);
						break;
					//鞋头
					case 6:
						shoes.setToe(cellValue);
						break;
					//跟型
					case 7:						
						shoes.setHotPoint(cellValue);
						break;
					//鞋靴类型
					case 8:
						shoes.setHeelStyle(cellValue);
						break;
					//沿口高度
					case 9:
						shoes.setUpperHeight(cellValue);
						break;
					//靴筒高度
					case 10:
						shoes.setBootHeight(cellValue);
						break;
					//皮质特征
					case 11:
						shoes.setLeather(cellValue);
						break;
					//鞋底材质
					case 12:
						shoes.setSole(cellValue);
						break;
					//图案
					case 13:
						shoes.setPattern(cellValue);
						break;
					//制作工艺
					case 14:
						shoes.setCraft(cellValue);
						break;
					//靴筒材质
					case 15:
						shoes.setBootMaterial(cellValue);
						break;
					//帮面材质
					case 16:
						shoes.setUpperMaterial(cellValue);
						break;
					//颜色
					case 17:
						shoes.setColor(cellValue);
						break;
					//流行元素
					case 18:
						shoes.setFashion(cellValue);
						break;
					//风格
					case 19:
						shoes.setStyle(cellValue);
						break;
					//内里材质
					case 20:
						shoes.setInnerMaterial(cellValue);
						break;
				    //Default
					case 21:
						if (!"N".equalsIgnoreCase(cellValue)&& !"Y".equalsIgnoreCase(cellValue)) {
							String tip = "添加失败，Defunct必须为N或Y";
							return tip;
						} else {
							if(cellValue == "Y"){
								shoes.setDefunct(true);
								onlineStore.setDefunct(true);
							}
							if(cellValue == "N")
								shoes.setDefunct(false);
								onlineStore.setDefunct(false);
							break;
						}
					//在线URL
					case 22:
						if (cellValue == null || "".equals(cellValue)) {
							String tip = "添加失败，在线URL不能为空";
							return tip;
						} else {
							OnlineStore store = onlineDAO.get(cellValue);
							if (store != null) {
								String tip = "添加失败，在线URL " + cellValue + " 已存在";
								return tip;
							} else {
								onlineStore.setOnlineUrl(cellValue);
								break;
							}
						}
					//价格
					case 23:
						if (cellValue == null || "".equals(cellValue)) {
							String tip = "添加失败，价格不能为空";
							return tip;
						} else {
							onlineStore.setPrice(Double.parseDouble(cellValue));
							break;
						}
					//图片URL
					case 24:
						String path = ServletActionContext.getServletContext().getRealPath(shoesId+"/"+cellValue);
						onlineStore.setImgUrl(path);
						break;
					//商店名称
					case 25:
						onlineStore.setStoreName(cellValue);
						break;
					//商店图片
					case 26:
						String dir = "/store";
						String targetDir = ServletActionContext.getServletContext().getRealPath(dir);
						File f = new File(targetDir);
						if(f.exists()==false){
							f.mkdirs();
						}		
						String storeImg_path = ServletActionContext.getServletContext().getRealPath(dir+"/"+cellValue);
						onlineStore.setStoreImg(storeImg_path);
						break;
					case 27:
						onlineStore.setFromEWeb(cellValue);
						break;
					}
				}
				//将从Excel中解析出来的对象放入数组中
				
				//点赞量默认为0
				shoes.setLike(0);
				onlineStore.setLike(0);
				shoesList.add(shoes);
				onLineStoreList.add(onlineStore);
			}
			
			//批量上传图片
			for (int i = 0; i < file.size(); i++) {
				String name = fileName.get(i).toString(); // 得到上传文件的原名称
				int index = name.indexOf("_");
				
				//如果索引值为-1，则格式错误
				if(index == -1){
					String tip = "图片命名格式错误";
					return tip;
				}
				
				//获取商品ID
				String subName = name.substring(0, index);
				String path = "/" + subName;
				String root = ServletActionContext.getServletContext().getRealPath(path);
				File dir = new File(root);
				if (dir.exists() == false) {
					String tip = "图片命名格式错误，商品ID不存在";
					return tip;
				} else {
					 InputStream is = new FileInputStream(file.get(i).toString());
					 File destFile = new File(root, name); // 获得存储文件
					 OutputStream os = new FileOutputStream(destFile);
					 byte[] buffer = new byte[400];
					 int length = 0;
					 while ((length = is.read(buffer)) > 0) {
						os.write(buffer, 0, length);
					 }
					is.close();
					os.close();
				}
			}
			
			for (int i = 0; i < file.size(); i++) {
				//获取文件名
				String filename = (String)fileName.get(i);
				//截取商品ID
				int index = filename.indexOf("_");
				//如果索引值为-1，则格式错误
				if(index == -1){
					String tip = "图片命名格式错误";
					return tip;
				}
				String shoesIdDir = filename.substring(0,index);
				//图片存储的相对路径
				String imgUrl = "/" + shoesIdDir + "/" + filename;		
				String shoesImgPath = ServletActionContext.getServletContext().getRealPath("/"+shoesIdDir);
				File dir = new File(shoesImgPath);
				if (dir.exists()) {
					Image image = new Image();
					String path = ServletActionContext.getServletContext().getRealPath(imgUrl);
					Image img = imageDAO.get(path);
					if(img==null){
					 image.setImageUrl(path);
					 image.setGoodsId(shoesIdDir);
					 //imageDAO.save(image);
					 imgList.add(image);
					}else{
						String tip = "上传图片失败，图片名"+filename+"已存在";
						return tip;
					}
				}
			}			
			shoesDAO.saveList(shoesList);
			shoesDAO.saveOnlineStoreList(onLineStoreList);
			imageDAO.save(imgList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
}
