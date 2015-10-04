package com.model;

import com.dao.*;
import com.util.ChineseInitial;
import java.util.ArrayList;
import java.util.List;

public class showBrand {
	
	private List<List<Brand>> allBrandlist;
/*	private List<Brand> abrandList;
	private List<Brand> bbrandList;
	private List<Brand> cbrandList;
	private List<Brand> dbrandList;
	private List<Brand> ebrandList;
	private List<Brand> fbrandList;
	private List<Brand> gbrandList;
	private List<Brand> hbrandList;
	private List<Brand> ibrandList;*/
	
/*	public List<Brand> getAbrandlist() {
		return abrandList;
	}

	public void setAbrandList(List<Brand> abrandList) {
		this.abrandList = abrandList;
	}
	
	public List<Brand> getBbrandlist() {
		return bbrandList;
	}

	public void setBbrandList(List<Brand> bbrandList) {
		this.bbrandList = bbrandList;
	}*/
	
	public List<List<Brand>> getAllbrandlist() {
		return allBrandlist;
	}

	public void setAllbrandList(List<List<Brand>> allBrandlist) {
		this.allBrandlist = allBrandlist;
	}
	
	public void classifybyInitial(){
		List<Brand> brandList = new ArrayList();
		BrandDAO brandDao = new BrandDAO();
		brandList = brandDao.findAll();
		
		allBrandlist = new ArrayList();
		List<Brand> abrandList = new ArrayList();
		List<Brand> bbrandList = new ArrayList();
		List<Brand> cbrandList = new ArrayList();
		List<Brand> dbrandList = new ArrayList();
		List<Brand> ebrandList = new ArrayList();
		List<Brand> fbrandList = new ArrayList();
		List<Brand> gbrandList = new ArrayList();
		List<Brand> hbrandList = new ArrayList();
		List<Brand> ibrandList = new ArrayList();
		List<Brand> jbrandList = new ArrayList();
		List<Brand> kbrandList = new ArrayList();
		List<Brand> lbrandList = new ArrayList();
		List<Brand> mbrandList = new ArrayList();
		List<Brand> nbrandList = new ArrayList();
		List<Brand> obrandList = new ArrayList();
		List<Brand> pbrandList = new ArrayList();
		List<Brand> qbrandList = new ArrayList();
		List<Brand> rbrandList = new ArrayList();
		List<Brand> sbrandList = new ArrayList();
		List<Brand> tbrandList = new ArrayList();
		List<Brand> ubrandList = new ArrayList();
		List<Brand> vbrandList = new ArrayList();
		List<Brand> wbrandList = new ArrayList();
		List<Brand> xbrandList = new ArrayList();
		List<Brand> ybrandList = new ArrayList();
		List<Brand> zbrandList = new ArrayList();

		
		ChineseInitial chinieseInit = new ChineseInitial();
		
		for (Brand brand:brandList){
			String firstletter  = chinieseInit.getFirstLetter(brand.getId().getBrandName());
			//System.out.println(firstletter);
			if (firstletter.equals("a")||firstletter.equals("A"))
				abrandList.add(brand);
			else if (firstletter.equals("b")||firstletter.equals("B"))
				bbrandList.add(brand);
			else if (firstletter.equals("c")||firstletter.equals("C"))
				cbrandList.add(brand);
			else if (firstletter.equals("d")||firstletter.equals("D"))
				dbrandList.add(brand);
			else if (firstletter.equals("e")||firstletter.equals("E"))
				ebrandList.add(brand);
			else if (firstletter.equals("f")||firstletter.equals("F"))
				fbrandList.add(brand);
			else if (firstletter.equals("g")||firstletter.equals("G"))
				gbrandList.add(brand);
			else if (firstletter.equals("h")||firstletter.equals("H"))
				hbrandList.add(brand);
			else if (firstletter.equals("i")||firstletter.equals("I"))
				ibrandList.add(brand);
			else if (firstletter.equals("j")||firstletter.equals("J"))
				jbrandList.add(brand);
			else if (firstletter.equals("k")||firstletter.equals("K"))
				kbrandList.add(brand);
			else if (firstletter.equals("l")||firstletter.equals("L"))
				lbrandList.add(brand);
			else if (firstletter.equals("m")||firstletter.equals("M"))
				mbrandList.add(brand);
			else if (firstletter.equals("n")||firstletter.equals("N"))
				nbrandList.add(brand);
			else if (firstletter.equals("o")||firstletter.equals("O"))
				obrandList.add(brand);
			else if (firstletter.equals("p")||firstletter.equals("P"))
				pbrandList.add(brand);
			else if (firstletter.equals("q")||firstletter.equals("Q"))
				qbrandList.add(brand);
			else if (firstletter.equals("r")||firstletter.equals("R"))
				rbrandList.add(brand);
			else if (firstletter.equals("s")||firstletter.equals("S"))
				sbrandList.add(brand);
			else if (firstletter.equals("t")||firstletter.equals("T"))
				tbrandList.add(brand);
			else if (firstletter.equals("u")||firstletter.equals("U"))
				ubrandList.add(brand);
			else if (firstletter.equals("v")||firstletter.equals("V"))
				vbrandList.add(brand);
			else if (firstletter.equals("w")||firstletter.equals("W"))
				wbrandList.add(brand);
			else if (firstletter.equals("x")||firstletter.equals("X"))
				xbrandList.add(brand);
			else if (firstletter.equals("y")||firstletter.equals("Y"))
				ybrandList.add(brand);
			else if (firstletter.equals("z")||firstletter.equals("Z"))
				zbrandList.add(brand);
		}
		
		/*System.out.println(ibrandList.size());*/
		allBrandlist.add(abrandList);
		allBrandlist.add(bbrandList);
		allBrandlist.add(cbrandList);
		allBrandlist.add(dbrandList);
		allBrandlist.add(ebrandList);
		allBrandlist.add(fbrandList);
		allBrandlist.add(gbrandList);
		allBrandlist.add(hbrandList);
		allBrandlist.add(ibrandList);
		allBrandlist.add(gbrandList);
		allBrandlist.add(kbrandList);
		allBrandlist.add(lbrandList);
		allBrandlist.add(mbrandList);
		allBrandlist.add(nbrandList);
		allBrandlist.add(obrandList);
		allBrandlist.add(pbrandList);
		allBrandlist.add(qbrandList);
		allBrandlist.add(rbrandList);
		allBrandlist.add(sbrandList);
		allBrandlist.add(tbrandList);
		allBrandlist.add(ubrandList);
		allBrandlist.add(vbrandList);
		allBrandlist.add(wbrandList);
		allBrandlist.add(xbrandList);
		allBrandlist.add(ybrandList);
		allBrandlist.add(zbrandList);	
		
	}
}