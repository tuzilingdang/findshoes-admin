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

	//Excel�ļ�
	private File uploadFile;
	private String uploadFileFileName;
	//ͼƬ�ļ�
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
		
		//�ж�Excel�ļ�����չ��
		int uploadFileIndex = uploadFileFileName.lastIndexOf('.');
		String uploadFileExt = uploadFileFileName.substring(uploadFileIndex).toLowerCase();
		boolean isExcel = true;
		if( !".xlsx".equals(uploadFileExt) && !".xls".equals(uploadFileExt)){
			isExcel = false;
		}
		
		//�ж�ͼƬ�ļ�����չ��
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
			// �����ϴ����ļ�����
			File target = new File(targetDirectory, uploadFileFileName);
			// ����ļ��Ѿ����ڣ�ɾ��ԭ�ļ�
			if (target.exists()) {
				target.delete();
			}
			
			// ����file����ʵ��Excel�ļ��ϴ�
			try {
				FileUtils.copyFile(uploadFile, target);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//����Excel�ļ����ϴ�ͼƬ�ļ�
			String tip = loadRoleInfo(uploadFileFileName,file,fileFileName);
			if (!"success".equals(tip)) {
				ActionContext.getContext().put("Tip", tip);
			} else {
				ActionContext.getContext().put("Tip", "��ӳɹ�!");
			}
		}else{
			ActionContext.getContext().put("Tip", "�ļ���ʽ����");
		}		
		return SUCCESS;
	}

	@Override
	public void validate() {
		// TODO Auto-generated method stub
		super.validate();
	}

	/**
	 * ��Excele����������ݣ���װ��һ��List,ͳһ�������ݿ�
	 * 
	 * @param uploadFileFileName
	 */
	public String loadRoleInfo(String uploadFileFileName,List file, List fileName) {

		//�ϴ���Excel�ļ��洢���ļ���
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
			// ����Excel��ͬ�汾
			try {
				wb = new XSSFWorkbook(fi);
			} catch (Exception e) {
				wb = new HSSFWorkbook(new FileInputStream(target));
			}
			Sheet sheet = wb.getSheetAt(0);
			// �õ�Excel������
			int rowNum = sheet.getPhysicalNumberOfRows();
			// �õ�Excel������
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
						switch (cell.getCellType()) { // �ж�excel��Ԫ�����ݵĸ�ʽ�����������ת�����Ա�������ݿ�
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

				
					// ͨ���������ж϶�Ӧ������ֶ�
					switch (j) {
					//Ь��ID
					case 0:
						if (cellValue == null || "".equals(cellValue)) {
							String tip = "���ʧ�ܣ�Ь��ID����Ϊ��";
							return tip;
						} else {
							shoesId = cellValue;
							Shoes s = shoesDAO.get(cellValue);
							if (s != null) {
								String tip = "���ʧ�ܣ�Ь��ID " + cellValue + " �Ѵ���";
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
				   //Ʒ��
					case 1:
						shoes.setBrand(cellValue);
						break;
					//����ʱ��
					case 2:
					//	DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
					//	Date date = format.parse(cellValue);
					//	shoes.setShowDate(new Timestamp(date.getTime()));
						shoes.setShowDate(cellValue);
						break;
					//����
					case 3:
						shoes.setSeason(cellValue);
						break;
					//����
					case 4:
						shoes.setOccasion(cellValue);
						break;
					//����
					case 5:
						shoes.setHeelHeight(cellValue);
						break;
					//Ьͷ
					case 6:
						shoes.setToe(cellValue);
						break;
					//����
					case 7:						
						shoes.setHotPoint(cellValue);
						break;
					//Ьѥ����
					case 8:
						shoes.setHeelStyle(cellValue);
						break;
					//�ؿڸ߶�
					case 9:
						shoes.setUpperHeight(cellValue);
						break;
					//ѥͲ�߶�
					case 10:
						shoes.setBootHeight(cellValue);
						break;
					//Ƥ������
					case 11:
						shoes.setLeather(cellValue);
						break;
					//Ь�ײ���
					case 12:
						shoes.setSole(cellValue);
						break;
					//ͼ��
					case 13:
						shoes.setPattern(cellValue);
						break;
					//��������
					case 14:
						shoes.setCraft(cellValue);
						break;
					//ѥͲ����
					case 15:
						shoes.setBootMaterial(cellValue);
						break;
					//�������
					case 16:
						shoes.setUpperMaterial(cellValue);
						break;
					//��ɫ
					case 17:
						shoes.setColor(cellValue);
						break;
					//����Ԫ��
					case 18:
						shoes.setFashion(cellValue);
						break;
					//���
					case 19:
						shoes.setStyle(cellValue);
						break;
					//�������
					case 20:
						shoes.setInnerMaterial(cellValue);
						break;
				    //Default
					case 21:
						if (!"N".equalsIgnoreCase(cellValue)&& !"Y".equalsIgnoreCase(cellValue)) {
							String tip = "���ʧ�ܣ�Defunct����ΪN��Y";
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
					//����URL
					case 22:
						if (cellValue == null || "".equals(cellValue)) {
							String tip = "���ʧ�ܣ�����URL����Ϊ��";
							return tip;
						} else {
							OnlineStore store = onlineDAO.get(cellValue);
							if (store != null) {
								String tip = "���ʧ�ܣ�����URL " + cellValue + " �Ѵ���";
								return tip;
							} else {
								onlineStore.setOnlineUrl(cellValue);
								break;
							}
						}
					//�۸�
					case 23:
						if (cellValue == null || "".equals(cellValue)) {
							String tip = "���ʧ�ܣ��۸���Ϊ��";
							return tip;
						} else {
							onlineStore.setPrice(Double.parseDouble(cellValue));
							break;
						}
					//ͼƬURL
					case 24:
						String path = ServletActionContext.getServletContext().getRealPath(shoesId+"/"+cellValue);
						onlineStore.setImgUrl(path);
						break;
					//�̵�����
					case 25:
						onlineStore.setStoreName(cellValue);
						break;
					//�̵�ͼƬ
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
				//����Excel�н��������Ķ������������
				
				//������Ĭ��Ϊ0
				shoes.setLike(0);
				onlineStore.setLike(0);
				shoesList.add(shoes);
				onLineStoreList.add(onlineStore);
			}
			
			//�����ϴ�ͼƬ
			for (int i = 0; i < file.size(); i++) {
				String name = fileName.get(i).toString(); // �õ��ϴ��ļ���ԭ����
				int index = name.indexOf("_");
				
				//�������ֵΪ-1�����ʽ����
				if(index == -1){
					String tip = "ͼƬ������ʽ����";
					return tip;
				}
				
				//��ȡ��ƷID
				String subName = name.substring(0, index);
				String path = "/" + subName;
				String root = ServletActionContext.getServletContext().getRealPath(path);
				File dir = new File(root);
				if (dir.exists() == false) {
					String tip = "ͼƬ������ʽ������ƷID������";
					return tip;
				} else {
					 InputStream is = new FileInputStream(file.get(i).toString());
					 File destFile = new File(root, name); // ��ô洢�ļ�
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
				//��ȡ�ļ���
				String filename = (String)fileName.get(i);
				//��ȡ��ƷID
				int index = filename.indexOf("_");
				//�������ֵΪ-1�����ʽ����
				if(index == -1){
					String tip = "ͼƬ������ʽ����";
					return tip;
				}
				String shoesIdDir = filename.substring(0,index);
				//ͼƬ�洢�����·��
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
						String tip = "�ϴ�ͼƬʧ�ܣ�ͼƬ��"+filename+"�Ѵ���";
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
