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
  
import org.apache.struts2.ServletActionContext;  
  
import com.opensymphony.xwork2.ActionSupport; 


public class uploadImgAction extends ActionSupport{
	private static final long serialVersionUID = 572146812454l;  
    private static final int BUFFER_SIZE = 16 * 1024;  
    private List<File> myFile = new ArrayList<File>();    
    private List<String> contentType = new ArrayList<String>();  
    private List<String> fileName = new ArrayList<String>();    //文件名  
    private List<String> imageFileName = new ArrayList<String>();  
    private String caption;  
    public String img_abpath;
    
    
    
	public Article article;
	private  java.util.Date date = new java.util.Date(); 
	Timestamp tmp_date = new Timestamp(date.getTime());
/*	private  java.sql.Date sql_now = new java.sql.Date(date.getTime()); 
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");*/
	

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
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
  
    @Override  
    public String execute() {  
        if (myFile == null)  
            return INPUT;  
        for (int i = 0; i < myFile.size(); i++) {  
            imageFileName.add(new Date().getTime()+ getExtention(this.getMyFileFileName().get(i))) ;  
            File imageFile = new File(ServletActionContext.getServletContext().getRealPath("UploadImages") + "/" + imageFileName);  
            //得到图片保存的位置(根据root来得到图片保存的路径在tomcat下的该工程里)   
//            System.out.println("kitty");
//            System.out.println(imageFile);
            copy(myFile.get(i), imageFile);  //把图片写入到上面设置的路径里  
            img_abpath = imageFile.getAbsolutePath();
        }  
        
        ArticleDAO articleDao =new ArticleDAO();
		article.setImgUrl(img_abpath);
		/*article.setImgUrl(img_abpath);	*/
		/*System.out.println(article.getArticalId());*/
		System.out.println("kitty");
		System.out.println(article.getTitle());
		System.out.println(article.getContent());
		System.out.println(article.getLead());
		System.out.println(img_abpath);

		article.setArticalId(0);
		article.setTitle(article.getTitle());
		article.setLead(article.getLead());
		/*article.setLead("hello kittarticle.getLead()y");
		article.setKeywords("hello kitty");*/
		article.setContent(article.getContent());
		article.setStatus(0);
		article.setAuthor("Administrator");	
		article.setDate(tmp_date);
		article.setDefunct("N");
		article.setTopLevel(0);
		System.out.println(article.getType());
		
		if(article.getType().equals("1")){
			article.setType(article.getType());
			article.setKeywords(article.getKeywords());
			System.out.println("b");
		}
		
		if (article.getType().equals("2")){
			article.setType(article.getType());
			article.setBwh(article.getBwh());
			article.setShoeUrl(article.getShoeUrl());
			article.setHeight(article.getHeight());
			article.setWeight(article.getWeight());
			article.setFootType(article.getFootType());
			article.setFootLength(article.getFootLength());
			article.setFootWidth(article.getFootWidth());
			System.out.println("a");
		}
		

		
		
		articleDao.save(article);  

		System.out.println("lead");
		System.out.println(article.getLead());
		System.out.println("keywords");
		System.out.println(article.getKeywords());
		
        return ActionSupport.SUCCESS;  
    }  
  
  
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
  
  
    public List<String> getImageFileName() {  
        return imageFileName;  
    }  
  
    public void setImageFileName(List<String> imageFileName) {  
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
}

