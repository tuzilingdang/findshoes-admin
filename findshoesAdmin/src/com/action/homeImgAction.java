package com.action;

import com.dao.ArticleDAO;
import com.model.Article;
import com.opensymphony.xwork2.ActionSupport;
import java.io.BufferedInputStream;  
import java.io.BufferedOutputStream;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.sql.Timestamp;
import java.util.ArrayList;  
import java.util.Date;  
import java.util.List;  

import javax.servlet.http.HttpServletRequest;
  
import org.apache.struts2.ServletActionContext;  
  
import com.opensymphony.xwork2.ActionSupport; 

public class homeImgAction extends ActionSupport{
	private static final long serialVersionUID = 572146812454l;  
    private static final int BUFFER_SIZE = 16 * 1024;  
    private List<File> myFile = new ArrayList<File>();   
    private List<String> contentType = new ArrayList<String>();  
    private List<String> fileName = new ArrayList<String>();
    private String imageFileName ;  
    private String caption;  
    public String img_abpath;
    public String articalId;
    
    public List<File> getMyFile() {  
        return myFile;  
    }  
  
    public void setMyFile(List<File> myFile) {  
        this.myFile = myFile;  
    }  
    
    public List<String> getContentType() {  
        return contentType;  
    }  
  
    public void setContentType(List<String> contentType) {  
        this.contentType = contentType;  
    }  
  
  
    public List<String> getMyFileFileName() {  
        return fileName;  
    }  
  
    public void setMyFileFileName(List<String> fileName) {  
        this.fileName = fileName;  
    }  
  
  
    public String getImageFileName() {  
        return imageFileName;  
    }  
  
    public void setImageFileName(String imageFileName) {  
        this.imageFileName = imageFileName;  
    }  
  
    public String getCaption() {  
        return caption;  
    }  
  
    public void setCaption(String caption) {  
        this.caption = caption;  
    }  
  
    public static int getBufferSize() {  
        return BUFFER_SIZE;  
    }  
    
    public String getArticalId() {  
        return articalId;  
    }  
  
    public void setArticalId(String articalId) {  
        this.articalId = articalId;  
    }  
    
    public String homePub(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String articalId = request.getParameter("articalId");	
    	System.out.println("homePub-articalId"+articalId);
       
        ArticleDAO articleDao =new ArticleDAO(); 
        Article art = new Article();
        art = articleDao.findById(Integer.valueOf(articalId).intValue());
        art.setDefunct("Y");
        articleDao.save(art);
        return ActionSupport.SUCCESS; 
	}

    public String homeImgupload(){
//		System.out.println(myFile.size());	
    	System.out.println("here-homeImgupload");
    	System.out.println("homeImgupload-articalId"+articalId);
        System.out.println(myFile.size());
        for (int i = 0; i < myFile.size(); i++) {  
            imageFileName= new Date().getTime()+ getExtention(this.getMyFileFileName().get(i)) ;  
            imageFileName = ServletActionContext.getServletContext().getRealPath("UploadImages/home") + "/" + imageFileName;
            File imageFile = new File(ServletActionContext.getServletContext().getRealPath("UploadImages/home") + "/" + imageFileName);  
            //得到图片保存的位置(根据root来得到图片保存的路径在tomcat下的该工程里)   
//            System.out.println("kitty");
            System.out.println("imageFileName"+imageFileName);
            copy(myFile.get(i), imageFile);  //把图片写入到上面设置的路径里  
            img_abpath = imageFile.getAbsolutePath();
        } 
        System.out.println(img_abpath);
       
        ArticleDAO articleDao =new ArticleDAO(); 
        Article art = new Article();
        art = articleDao.findById(Integer.valueOf(articalId).intValue());
        System.out.println("art："+art.getArticalId());
        art.setDefunct("Y");
        art.setImgPath(imageFileName);
        articleDao.save(art);
        return ActionSupport.SUCCESS; 
	}
	
    private static void copy(File src, File dst) {  
        try {  
            InputStream in = null;  
            OutputStream out = null;  
            try {  
                in = new BufferedInputStream(new FileInputStream(src),  
                        BUFFER_SIZE);  
                out = new BufferedOutputStream(new FileOutputStream(dst),  
                        BUFFER_SIZE);  
                byte[] buffer = new byte[BUFFER_SIZE];  
                while (in.read(buffer) > 0) {  
                    out.write(buffer);  
                }  
            } finally {  
                if (null != in) {  
                    in.close();  
                }  
                if (null != out) {  
                    out.close();  
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
      
    private static String getExtention(String fileName) {  
        int pos = fileName.lastIndexOf(".");  
        return fileName.substring(pos);  
    }  
}