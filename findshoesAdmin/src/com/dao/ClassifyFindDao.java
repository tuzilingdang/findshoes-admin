//专门处理分类查找的数据库连接
package com.dao;

import com.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassifyFindDao extends BaseHibernateDAO{
	
	private List<Shoes> shoesList;
	
	//分类找鞋列表的主函数
	public List<Shoes> classifyFindShoes(Map<String, String> map) {
		try {
			
			String s = null;	//存放当前待处理的属性数组
			//在同一级里面的多个是并列关系，但是不同级里面是级联关系
			for(String keys:map.keySet()){
				//已经获得map，注意这里的map中本身放的就是一个String[]
				
				
				System.out.println("goPage here the question ");
				System.out.println("goPage here the question keys= "+keys);
				System.out.println("goPage here the question map.keySet()= "+map.keySet());
				
				if(keys.equals("shGoodsid")){
					List<Shoes> shoesQlist = new ArrayList();
					s=map.get(keys);
					String hql = "from Shoes as shoes where ";
					hql += "shoes.goodsId= '"+s+"' ";
					
					System.out.println(hql);  //hql ok
					Query queryObject = getSession().createQuery(hql);
					shoesQlist = queryObject.list();
					/*System.out.println(shoesQlist.size());  //hql ok
*/					if(shoesQlist==null)//如果没查到品牌号，直接下一次循环
						continue;
					
					if(shoesList!=null){
						//这里可以直接筛选鞋子
						int j;
						for(int i=0;i<shoesList.size();i++){
							for(j=0;j<shoesQlist.size();j++)
								if(shoesList.get(i).getBrand().equals(shoesQlist.get(j).getBrand()))
									break;
								
							if(j>=shoesQlist.size()){
								//没找到符合的，直接删掉
								shoesList.remove(i);
								i--;
							}
						}
					}else{
						//shoesList==null
						shoesList = new ArrayList<Shoes>();
						//获得了brandList，接着查找shoes了，生成shoesList
						shoesList = shoesQlist;
					}				
					System.out.println("货号后，shoesList.size == "+shoesList.size());
				}
				
				//Brand
				if(keys.equals("shBrand")){
					List<Shoes> shoesQlist;
					//可能有多个并列，s中存放的就是ckBrand的所有元素
					s=map.get(keys);

					
					//已经获得了ckBrand的所有，可以去寻找鞋子列表了
					String hql = "from Shoes as shoes where ";
						hql += "shoes.brand= '"+s+"' ";
					
					System.out.println(hql);  //hql ok
					Query queryObject = getSession().createQuery(hql);
					shoesQlist = queryObject.list();
					
					System.out.println("queryObject.list == "+queryObject.list().size());
					if(shoesQlist==null)//如果没查到品牌号，直接下一次循环
						continue;
					
					//测试brandList ok
					System.out.println("brandList.size == "+shoesQlist.size());
//					System.out.println(brandList.get(0).getBrandName());
					
					//因为是hash表，位置不确定，这里也要判断一下shoesList是否为null
					if(shoesList!=null){
						//这里可以直接筛选鞋子
						int j;
						for(int i=0;i<shoesList.size();i++){
							for(j=0;j<shoesQlist.size();j++)
								if(shoesList.get(i).getBrand().equals(shoesQlist.get(j).getBrand()))
									break;
								
							if(j>=shoesQlist.size()){
								//没找到符合的，直接删掉
								shoesList.remove(i);
								i--;
							}
						}
					}else{
						//shoesList==null
						shoesList = new ArrayList<Shoes>();
						//获得了brandList，接着查找shoes了，生成shoesList
						shoesList = shoesQlist;
					}		
					System.out.println("Brand后，shoesList.size == "+shoesList.size());
//					System.out.println(shoesList.get(0).getGoodsId());
				}//if(ckBrand) 这里获取了所有品牌的鞋子
						
				
				//HeelHeight
				else if(keys.equals("shHeight")){
					s=map.get(keys);		

					if(shoesList!=null){
						//已有，直接检查，不符合删除
						for(int i=0;i<shoesList.size();i++){
//							System.out.println("i == "+i);
							int flag=0;

							if(shoesList.get(i).getHeelHeight()==null)
								break;
							if(shoesList.get(i).getHeelHeight().contains(s)){
								flag=1;
								break;
							}			
							if(flag==0){
								shoesList.remove(i);
								i--;
							}
						}						
					}
					else{
						//shoesList==null
						shoesList = new ArrayList<Shoes>();
						String hql = "from Shoes as shoes where";
						int flag=0;
						
						if(flag == 0)
							hql += " shoes.heelHeight like '%"+s+"%'";
						else
							hql += " or shoes.heelHeight like '%"+s+"%'";
						
						flag=1;
							
						System.out.println("ckHeelHeight,为null  == "+hql);
						Query queryObject = getSession().createQuery(hql);
						queryObject = getSession().createQuery(hql);
						shoesList = queryObject.list();
					}
					System.out.println("HeelHeight后，shoesList.size == "+shoesList.size());				
				}//if(ckHeelHeight)
				
				
/******************************选购热点***************************************************/				
				else if(keys.equals("shHotpoint")){
					s=map.get(keys);
					if(shoesList!=null){
						//已有，直接检查，不符合删除
						for(int i=0;i<shoesList.size();i++){
//							System.out.println("i == "+i);
							int flag=0;
				
							if(shoesList.get(i).getHotPoint()==null)
								break;
//								System.out.println(shoesList.get(i).getHeelHeight());
							if(shoesList.get(i).getHotPoint().contains(s)){
								flag=1;
								break;
							}			
							if(flag==0){
								shoesList.remove(i);
								i--;
							}
//							System.out.println("shoesList.size == "+shoesList.size());
						}						
					}
					else{
						//shoesList==null
						shoesList = new ArrayList<Shoes>();
						String hql = "from Shoes as shoes where ";
						int flag=0;
						
						if(flag == 0)
							hql += " shoes.hotPoint like '%"+s+"%'";
						else
							hql += " or shoes.hotPoint like '%"+s+"%'";
							
							flag=1;				
						System.out.println("ckHotPoint,为null  == "+hql);
						Query queryObject = getSession().createQuery(hql);
						queryObject = getSession().createQuery(hql);
						shoesList = queryObject.list();
					}
					
					System.out.println("HotPoint后，shoesList.size == "+shoesList.size());
					
				}//if(ckHeelHeight)
				
				/******************************流行元素***************************************************/				
				else if(keys.equals("shFashion")){
					s=map.get(keys);
					if(shoesList!=null){
						//已有，直接检查，不符合删除
						for(int i=0;i<shoesList.size();i++){
//							System.out.println("i == "+i);
							int flag=0;
						
							if(shoesList.get(i).getFashion()==null)
								break;
//								System.out.println(shoesList.get(i).getHeelHeight());
							if(shoesList.get(i).getFashion().contains(s)){
								flag=1;
								break;
							}
							
							
							if(flag==0){
								shoesList.remove(i);
								i--;
							}
//							System.out.println("shoesList.size == "+shoesList.size());
						}						
					}
					else{
						//shoesList==null
						shoesList = new ArrayList<Shoes>();
						String hql = "from Shoes as shoes where ";
						int flag=0;
						
						if(flag == 0)
							hql += " shoes.fashion like '%"+s+"%'";
						else
							hql += " or shoes.fashion like '%"+s+"%'";
						
						flag=1;
										
						System.out.println("ckFashion,为null  == "+hql);
						Query queryObject = getSession().createQuery(hql);
						queryObject = getSession().createQuery(hql);
						shoesList = queryObject.list();
					}
					
					System.out.println("Popelements后，shoesList.size == "+shoesList.size());
					
				}//if(ckHeelHeight)
				
/******************************场合***************************************************/				
				else if(keys.equals("shOccasion")){
					s=map.get(keys);
					
					if(shoesList!=null){
						//已有，直接检查，不符合删除
						for(int i=0;i<shoesList.size();i++){
//							System.out.println("i == "+i);
							int flag=0;
							
							if(shoesList.get(i).getOccasion()==null)
								break;
//								System.out.println(shoesList.get(i).getHeelHeight());
							if(shoesList.get(i).getOccasion().contains(s)){
								flag=1;
								break;
							}
							
							if(flag==0){
								shoesList.remove(i);
								i--;
							}
//							System.out.println("shoesList.size == "+shoesList.size());
						}						
					}
					else{
						//shoesList==null
						String hql = "from Shoes as shoes where ";
						int flag=0;
						if(flag == 0)
							hql += " shoes.occasion like '%"+s+"%'";
						else
							hql += " or shoes.occasion like '%"+s+"%'";
						
						flag=1;
											
						System.out.println("ckoccasion为null  == "+hql);
						Query queryObject = getSession().createQuery(hql);
						queryObject = getSession().createQuery(hql);
						shoesList = queryObject.list();
					}				
					System.out.println("occasion后，shoesList.size == "+shoesList.size());			
				}//if(ckHeelHeight)
				
/******************************风格***************************************************/				
				else if(keys.equals("shStyle")){
					s=map.get(keys);					
						if(shoesList!=null){
							//已有，直接检查，不符合删除
							for(int i=0;i<shoesList.size();i++){
//								System.out.println("i == "+i);
								int flag=0;
								if(shoesList.get(i).getStyle()==null)
									break;
//									System.out.println(shoesList.get(i).getHeelHeight());
//									System.out.println(tmp);
								if(shoesList.get(i).getStyle().contains(s)){
									flag=1;
									break;
								}
								
								if(flag==0){
									shoesList.remove(i);
									i--;
								}
//								System.out.println("shoesList.size == "+shoesList.size());
							}						
						}
						else{
							//shoesList==null
							String hql = "from Shoes as shoes where ";
							int flag=0;
							if(flag == 0)
								hql += " shoes.style like '%"+s+"%'";
							else
								hql += " or shoes.style like '%"+s+"%'";
							
							flag=1;
											
							System.out.println("ckStyle,为null  == "+hql);
							Query queryObject = getSession().createQuery(hql);
							queryObject = getSession().createQuery(hql);
							shoesList = queryObject.list();
						}				
						System.out.println("Toe后，shoesList.size == "+shoesList.size());			
					}//if(ckHeelHeight)
				
				//Toecap
				else if(keys.equals("shToe")){
					s=map.get(keys);
					
					if(shoesList!=null){
						//已有，直接检查，不符合删除
						for(int i=0;i<shoesList.size();i++){
//							System.out.println("i == "+i);
							int flag=0;
					
							if(shoesList.get(i).getToe()==null)
								break;
//								System.out.println(shoesList.get(i).getHeelHeight());
//								System.out.println(s);
							if(shoesList.get(i).getToe().contains(s)){
								flag=1;
								break;
							}
						
							
							if(flag==0){
								shoesList.remove(i);
								i--;
							}
//							System.out.println("shoesList.size == "+shoesList.size());
						}						
					}
					else{
						//shoesList==null
						String hql = "from Shoes as shoes where ";
						int flag=0;
						if(flag == 0)
							hql += " shoes.toe like '%"+s+"%'";
						else
							hql += " or shoes.toe like '%"+s+"%'";
						
						flag=1;
											
						System.out.println("ckToe,为null  == "+hql);
						Query queryObject = getSession().createQuery(hql);
						queryObject = getSession().createQuery(hql);
						shoesList = queryObject.list();
					}				
					System.out.println("Toe后，shoesList.size == "+shoesList.size());			
				}//if(ckHeelHeight)
				
				//Toecap
				else if(keys.equals("shLeather")){
					s=map.get(keys);
					
					if(shoesList!=null){
						//已有，直接检查，不符合删除
						for(int i=0;i<shoesList.size();i++){
//							System.out.println("i == "+i);
							int flag=0;
							if(shoesList.get(i).getHeelStyle()==null)
								break;
//								System.out.println(shoesList.get(i).getHeelHeight());
							if(shoesList.get(i).getHeelStyle().contains(s)){
								flag=1;
								break;
							}
						
							
							if(flag==0){
								shoesList.remove(i);
								i--;
							}
//							System.out.println("shoesList.size == "+shoesList.size());
						}						
					}
					else{
						//shoesList==null
						String hql = "from Shoes as shoes where ";
						int flag=0;
						
						if(flag == 0)
							hql += " shoes.leather like '%"+s+"%'";
						else
							hql += " or shoes.leather like '%"+s+"%'";
						
						flag=1;
												
						System.out.println("ckleather,为null  == "+hql);
						Query queryObject = getSession().createQuery(hql);
						queryObject = getSession().createQuery(hql);
						shoesList = queryObject.list();
					}				
					System.out.println("leather后，shoesList.size == "+shoesList.size());			
				}//if(ckHeelHeight)
				
				//Toecap
				else if(keys.equals("shSole")){
					s=map.get(keys);
					
					if(shoesList!=null){
						//已有，直接检查，不符合删除
						for(int i=0;i<shoesList.size();i++){
//							System.out.println("i == "+i);
							int flag=0;
						
							if(shoesList.get(i).getSole()==null)
								break;
//								System.out.println(shoesList.get(i).getHeelHeight());
							if(shoesList.get(i).getSole().contains(s)){
								flag=1;
								break;
							}
			
							if(flag==0){
								shoesList.remove(i);
								i--;
							}
//							System.out.println("shoesList.size == "+shoesList.size());
						}						
					}
					else{
						//shoesList==null
						String hql = "from Shoes as shoes where ";
						int flag=0;
						if(flag == 0)
							hql += " shoes.sole like '%"+s+"%'";
						else
							hql += " or shoes.sole like '%"+s+"%'";
						
						flag=1;
						
						System.out.println("ckleather,为null  == "+hql);
						Query queryObject = getSession().createQuery(hql);
						queryObject = getSession().createQuery(hql);
						shoesList = queryObject.list();
					}				
					System.out.println("sole后，shoesList.size == "+shoesList.size());			
				}//if(ckHeelHeight)
				
				//Toecap
				else if(keys.equals("shUpperHeight")){
					s=map.get(keys);
					
					if(shoesList!=null){
						//已有，直接检查，不符合删除
						for(int i=0;i<shoesList.size();i++){
//							System.out.println("i == "+i);
							int flag=0;
							
							if(shoesList.get(i).getUpperHeight()==null)
								break;
//								System.out.println(shoesList.get(i).getHeelHeight());
//								System.out.println(tmp);
							if(shoesList.get(i).getUpperHeight().contains(s)){
								flag=1;
								break;
							}
								
							if(flag==0){
								shoesList.remove(i);
								i--;
							}
//							System.out.println("shoesList.size == "+shoesList.size());
						}						
					}
					else{
						//shoesList==null
						String hql = "from Shoes as shoes where ";
						int flag=0;
					
						if(flag == 0)
							hql += " shoes.upperHeight like '%"+s+"%'";
						else
							hql += " or shoes.upperHeight like '%"+s+"%'";
						
						flag=1;
												
						System.out.println("ckleather,为null  == "+hql);
						Query queryObject = getSession().createQuery(hql);
						queryObject = getSession().createQuery(hql);
						shoesList = queryObject.list();
					}				
					System.out.println("sole后，shoesList.size == "+shoesList.size());			
				}//if(ckHeelHeight)
		
				//Innermaterial
				else if(keys.equals("shInnerMaterial")){
					s=map.get(keys);
					
					if(shoesList!=null){
						//已有，直接检查，不符合删除
						for(int i=0;i<shoesList.size();i++){
//							System.out.println("i == "+i);
							int flag=0;
						
							if(shoesList.get(i).getSole()==null)
								break;
//								System.out.println(shoesList.get(i).getHeelHeight());
							if(shoesList.get(i).getSole().contains(s)){
								flag=1;
								break;
							}
			
							if(flag==0){
								shoesList.remove(i);
								i--;
							}
//							System.out.println("shoesList.size == "+shoesList.size());
						}						
					}
					else{
						//shoesList==null
						String hql = "from Shoes as shoes where ";
						int flag=0;
						if(flag == 0)
							hql += " shoes.innerMaterial like '%"+s+"%'";
						else
							hql += " or shoes.innerMaterial like '%"+s+"%'";
						
						flag=1;
						
						System.out.println("ckleather,为null  == "+hql);
						Query queryObject = getSession().createQuery(hql);
						queryObject = getSession().createQuery(hql);
						shoesList = queryObject.list();
					}				
					System.out.println("innerMaterial后，shoesList.size == "+shoesList.size());			
				}//if(ckHeelHeight)
				
				//Innermaterial
				else if(keys.equals("shUpperMaterial")){
					s=map.get(keys);
					
					if(shoesList!=null){
						//已有，直接检查，不符合删除
						for(int i=0;i<shoesList.size();i++){
//							System.out.println("i == "+i);
							int flag=0;
						
							if(shoesList.get(i).getSole()==null)
								break;
//								System.out.println(shoesList.get(i).getHeelHeight());
							if(shoesList.get(i).getSole().contains(s)){
								flag=1;
								break;
							}
			
							if(flag==0){
								shoesList.remove(i);
								i--;
							}
//							System.out.println("shoesList.size == "+shoesList.size());
						}						
					}
					else{
						//shoesList==null
						String hql = "from Shoes as shoes where ";
						int flag=0;
						if(flag == 0)
							hql += " shoes.upperMaterial like '%"+s+"%'";
						else
							hql += " or shoes.upperMaterial like '%"+s+"%'";
						
						flag=1;
						
						System.out.println("upper,为null  == "+hql);
						Query queryObject = getSession().createQuery(hql);
						queryObject = getSession().createQuery(hql);
						shoesList = queryObject.list();
					}				
					System.out.println("upperMaterial后，shoesList.size == "+shoesList.size());			
				}//if(ckHeelHeight)
			
				//Season
				else if(keys.equals("shSeason")){
					List<Shoes> shoesQlist;
					s=map.get(keys);
					String[] ss = new String[2];
	
						if(s.equals("春季")){
							ss[0]="春";
						}
						
						else if(s.equals("秋季"))
							ss[0]="秋";
						
						else if(s.equals("夏季"))
							ss[0]="夏";
						
						else if(s.equals("冬季"))
							ss[0]="冬";
					
					
					//要判断一下 shoesList 为不为 null，如果是 null，则要查数据库重新获取shoesList
					if(shoesList!=null){
						//这里可以直接筛选鞋子
						int j;
						for(int i=0;i<shoesList.size();i++){
							for(j=0;j<ss.length;j++){
								if(shoesList.get(i).getSeason()!=null){
									if(shoesList.get(i).getSeason().contains(ss[j]))
										break;
								}
								else{
									return null;
								}
							}

							
							if(j>=ss.length){
								shoesList.remove(i);
								i--;
							}
						}
						
					}else{
						//shoesList==null
						shoesList = new ArrayList<Shoes>();
						String hql = "from Shoes as shoes where ";
						int flag=0;
				
							if(s.equals("春季")){
								hql += "shoes.season like '%春%'";
								flag=1;
							}
							
							else if(s.equals("秋季")){
								if(flag == 0)
									hql += " shoes.season like '%秋%'";
								else
									hql += " or shoes.season like '%秋%'";
								
								flag=1;
							}
							
							else if(s.equals("夏季")){
								if(flag == 0)
									hql += " shoes.season like '%夏%'";
								else
									hql += " or shoes.season like '%夏%'";
								
								flag=1;
							}
							
							else if(s.equals("冬季")){
								if(flag == 0)
									hql += " shoes.season like '%冬%'";
								else
									hql += " or shoes.season like '%冬%'";
							}
						
						
//						System.out.println("ckSeason,为null  == "+hql);
						Query queryObject = getSession().createQuery(hql);
						queryObject = getSession().createQuery(hql);
						shoesQlist = queryObject.list();
						shoesList = shoesQlist;
						System.out.println("Season后，shoesList.size == "+shoesList.size());
					}
				}//if(ckSeason)
				

				
	
			}
			return shoesList;
			
		}catch (RuntimeException re) {
			throw re;
		}
	}
	
}
