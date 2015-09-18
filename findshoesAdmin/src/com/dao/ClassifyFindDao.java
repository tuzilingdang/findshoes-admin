//ר�Ŵ��������ҵ����ݿ�����
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
	
	//������Ь�б��������
	public List<Shoes> classifyFindShoes(Map<String, String> map) {
		try {
			
			String s = null;	//��ŵ�ǰ���������������
			//��ͬһ������Ķ���ǲ��й�ϵ�����ǲ�ͬ�������Ǽ�����ϵ
			for(String keys:map.keySet()){
				//�Ѿ����map��ע�������map�б���ŵľ���һ��String[]
				
				
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
*/					if(shoesQlist==null)//���û�鵽Ʒ�ƺţ�ֱ����һ��ѭ��
						continue;
					
					if(shoesList!=null){
						//�������ֱ��ɸѡЬ��
						int j;
						for(int i=0;i<shoesList.size();i++){
							for(j=0;j<shoesQlist.size();j++)
								if(shoesList.get(i).getBrand().equals(shoesQlist.get(j).getBrand()))
									break;
								
							if(j>=shoesQlist.size()){
								//û�ҵ����ϵģ�ֱ��ɾ��
								shoesList.remove(i);
								i--;
							}
						}
					}else{
						//shoesList==null
						shoesList = new ArrayList<Shoes>();
						//�����brandList�����Ų���shoes�ˣ�����shoesList
						shoesList = shoesQlist;
					}				
					System.out.println("���ź�shoesList.size == "+shoesList.size());
				}
				
				//Brand
				if(keys.equals("shBrand")){
					List<Shoes> shoesQlist;
					//�����ж�����У�s�д�ŵľ���ckBrand������Ԫ��
					s=map.get(keys);

					
					//�Ѿ������ckBrand�����У�����ȥѰ��Ь���б���
					String hql = "from Shoes as shoes where ";
						hql += "shoes.brand= '"+s+"' ";
					
					System.out.println(hql);  //hql ok
					Query queryObject = getSession().createQuery(hql);
					shoesQlist = queryObject.list();
					
					System.out.println("queryObject.list == "+queryObject.list().size());
					if(shoesQlist==null)//���û�鵽Ʒ�ƺţ�ֱ����һ��ѭ��
						continue;
					
					//����brandList ok
					System.out.println("brandList.size == "+shoesQlist.size());
//					System.out.println(brandList.get(0).getBrandName());
					
					//��Ϊ��hash��λ�ò�ȷ��������ҲҪ�ж�һ��shoesList�Ƿ�Ϊnull
					if(shoesList!=null){
						//�������ֱ��ɸѡЬ��
						int j;
						for(int i=0;i<shoesList.size();i++){
							for(j=0;j<shoesQlist.size();j++)
								if(shoesList.get(i).getBrand().equals(shoesQlist.get(j).getBrand()))
									break;
								
							if(j>=shoesQlist.size()){
								//û�ҵ����ϵģ�ֱ��ɾ��
								shoesList.remove(i);
								i--;
							}
						}
					}else{
						//shoesList==null
						shoesList = new ArrayList<Shoes>();
						//�����brandList�����Ų���shoes�ˣ�����shoesList
						shoesList = shoesQlist;
					}		
					System.out.println("Brand��shoesList.size == "+shoesList.size());
//					System.out.println(shoesList.get(0).getGoodsId());
				}//if(ckBrand) �����ȡ������Ʒ�Ƶ�Ь��
						
				
				//HeelHeight
				else if(keys.equals("shHeight")){
					s=map.get(keys);		

					if(shoesList!=null){
						//���У�ֱ�Ӽ�飬������ɾ��
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
							
						System.out.println("ckHeelHeight,Ϊnull  == "+hql);
						Query queryObject = getSession().createQuery(hql);
						queryObject = getSession().createQuery(hql);
						shoesList = queryObject.list();
					}
					System.out.println("HeelHeight��shoesList.size == "+shoesList.size());				
				}//if(ckHeelHeight)
				
				
/******************************ѡ���ȵ�***************************************************/				
				else if(keys.equals("shHotpoint")){
					s=map.get(keys);
					if(shoesList!=null){
						//���У�ֱ�Ӽ�飬������ɾ��
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
						System.out.println("ckHotPoint,Ϊnull  == "+hql);
						Query queryObject = getSession().createQuery(hql);
						queryObject = getSession().createQuery(hql);
						shoesList = queryObject.list();
					}
					
					System.out.println("HotPoint��shoesList.size == "+shoesList.size());
					
				}//if(ckHeelHeight)
				
				/******************************����Ԫ��***************************************************/				
				else if(keys.equals("shFashion")){
					s=map.get(keys);
					if(shoesList!=null){
						//���У�ֱ�Ӽ�飬������ɾ��
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
										
						System.out.println("ckFashion,Ϊnull  == "+hql);
						Query queryObject = getSession().createQuery(hql);
						queryObject = getSession().createQuery(hql);
						shoesList = queryObject.list();
					}
					
					System.out.println("Popelements��shoesList.size == "+shoesList.size());
					
				}//if(ckHeelHeight)
				
/******************************����***************************************************/				
				else if(keys.equals("shOccasion")){
					s=map.get(keys);
					
					if(shoesList!=null){
						//���У�ֱ�Ӽ�飬������ɾ��
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
											
						System.out.println("ckoccasionΪnull  == "+hql);
						Query queryObject = getSession().createQuery(hql);
						queryObject = getSession().createQuery(hql);
						shoesList = queryObject.list();
					}				
					System.out.println("occasion��shoesList.size == "+shoesList.size());			
				}//if(ckHeelHeight)
				
/******************************���***************************************************/				
				else if(keys.equals("shStyle")){
					s=map.get(keys);					
						if(shoesList!=null){
							//���У�ֱ�Ӽ�飬������ɾ��
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
											
							System.out.println("ckStyle,Ϊnull  == "+hql);
							Query queryObject = getSession().createQuery(hql);
							queryObject = getSession().createQuery(hql);
							shoesList = queryObject.list();
						}				
						System.out.println("Toe��shoesList.size == "+shoesList.size());			
					}//if(ckHeelHeight)
				
				//Toecap
				else if(keys.equals("shToe")){
					s=map.get(keys);
					
					if(shoesList!=null){
						//���У�ֱ�Ӽ�飬������ɾ��
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
											
						System.out.println("ckToe,Ϊnull  == "+hql);
						Query queryObject = getSession().createQuery(hql);
						queryObject = getSession().createQuery(hql);
						shoesList = queryObject.list();
					}				
					System.out.println("Toe��shoesList.size == "+shoesList.size());			
				}//if(ckHeelHeight)
				
				//Toecap
				else if(keys.equals("shLeather")){
					s=map.get(keys);
					
					if(shoesList!=null){
						//���У�ֱ�Ӽ�飬������ɾ��
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
												
						System.out.println("ckleather,Ϊnull  == "+hql);
						Query queryObject = getSession().createQuery(hql);
						queryObject = getSession().createQuery(hql);
						shoesList = queryObject.list();
					}				
					System.out.println("leather��shoesList.size == "+shoesList.size());			
				}//if(ckHeelHeight)
				
				//Toecap
				else if(keys.equals("shSole")){
					s=map.get(keys);
					
					if(shoesList!=null){
						//���У�ֱ�Ӽ�飬������ɾ��
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
						
						System.out.println("ckleather,Ϊnull  == "+hql);
						Query queryObject = getSession().createQuery(hql);
						queryObject = getSession().createQuery(hql);
						shoesList = queryObject.list();
					}				
					System.out.println("sole��shoesList.size == "+shoesList.size());			
				}//if(ckHeelHeight)
				
				//Toecap
				else if(keys.equals("shUpperHeight")){
					s=map.get(keys);
					
					if(shoesList!=null){
						//���У�ֱ�Ӽ�飬������ɾ��
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
												
						System.out.println("ckleather,Ϊnull  == "+hql);
						Query queryObject = getSession().createQuery(hql);
						queryObject = getSession().createQuery(hql);
						shoesList = queryObject.list();
					}				
					System.out.println("sole��shoesList.size == "+shoesList.size());			
				}//if(ckHeelHeight)
		
				//Innermaterial
				else if(keys.equals("shInnerMaterial")){
					s=map.get(keys);
					
					if(shoesList!=null){
						//���У�ֱ�Ӽ�飬������ɾ��
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
						
						System.out.println("ckleather,Ϊnull  == "+hql);
						Query queryObject = getSession().createQuery(hql);
						queryObject = getSession().createQuery(hql);
						shoesList = queryObject.list();
					}				
					System.out.println("innerMaterial��shoesList.size == "+shoesList.size());			
				}//if(ckHeelHeight)
				
				//Innermaterial
				else if(keys.equals("shUpperMaterial")){
					s=map.get(keys);
					
					if(shoesList!=null){
						//���У�ֱ�Ӽ�飬������ɾ��
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
						
						System.out.println("upper,Ϊnull  == "+hql);
						Query queryObject = getSession().createQuery(hql);
						queryObject = getSession().createQuery(hql);
						shoesList = queryObject.list();
					}				
					System.out.println("upperMaterial��shoesList.size == "+shoesList.size());			
				}//if(ckHeelHeight)
			
				//Season
				else if(keys.equals("shSeason")){
					List<Shoes> shoesQlist;
					s=map.get(keys);
					String[] ss = new String[2];
	
						if(s.equals("����")){
							ss[0]="��";
						}
						
						else if(s.equals("�＾"))
							ss[0]="��";
						
						else if(s.equals("�ļ�"))
							ss[0]="��";
						
						else if(s.equals("����"))
							ss[0]="��";
					
					
					//Ҫ�ж�һ�� shoesList Ϊ��Ϊ null������� null����Ҫ�����ݿ����»�ȡshoesList
					if(shoesList!=null){
						//�������ֱ��ɸѡЬ��
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
				
							if(s.equals("����")){
								hql += "shoes.season like '%��%'";
								flag=1;
							}
							
							else if(s.equals("�＾")){
								if(flag == 0)
									hql += " shoes.season like '%��%'";
								else
									hql += " or shoes.season like '%��%'";
								
								flag=1;
							}
							
							else if(s.equals("�ļ�")){
								if(flag == 0)
									hql += " shoes.season like '%��%'";
								else
									hql += " or shoes.season like '%��%'";
								
								flag=1;
							}
							
							else if(s.equals("����")){
								if(flag == 0)
									hql += " shoes.season like '%��%'";
								else
									hql += " or shoes.season like '%��%'";
							}
						
						
//						System.out.println("ckSeason,Ϊnull  == "+hql);
						Query queryObject = getSession().createQuery(hql);
						queryObject = getSession().createQuery(hql);
						shoesQlist = queryObject.list();
						shoesList = shoesQlist;
						System.out.println("Season��shoesList.size == "+shoesList.size());
					}
				}//if(ckSeason)
				

				
	
			}
			return shoesList;
			
		}catch (RuntimeException re) {
			throw re;
		}
	}
	
}
