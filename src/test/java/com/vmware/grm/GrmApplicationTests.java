package com.vmware.grm;

import com.vmware.grm.dao.LanguangesDao;
import com.vmware.grm.dao.ProductLanguageDao;
import com.vmware.grm.dao.ProductsDao;
import com.vmware.grm.dao.ReleaseprofilesDao;
import com.vmware.grm.model.Languages;
import com.vmware.grm.model.Products;
import com.vmware.grm.utils.MyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GrmApplicationTests {

	private final boolean te = false;

	@Autowired
	private LanguangesDao languangesDao;

	@Autowired
	private ProductsDao productsDao;

	@Autowired
	private ProductLanguageDao productLanguageDao;

	@Autowired
	private ReleaseprofilesDao releaseprofilesDao;

	@Test
	public void listLanguagesByLimit() {
//		List<Languages> languages = languangesDao.listLanguagesByLimit();
//		languages.forEach(e-> System.out.println(e));
	}

	@Test
	public void getLanguageByName() {
		Languages languages = null;
		try {
			languages = languangesDao.getLanguageByName("Span");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(languages);
	}

	@Test
	public void getLanguageByCode() {
		Languages languages = null;
		try {
			languages = languangesDao.getLanguageByCode("ko");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(languages);
	}

	@Test
	public void insertLanguage() {
		UUID uuid = MyUtil.getUuid();
		System.out.println(uuid);
//		int i = languangesDao.insertLanguage( new Languages(uuid.toString(),"wwwwwwww", "aaaaaaaaaa", null, "zzzzzz"));
//		System.out.println(i);
	}

	@Test
	public void getLanguageById() throws Exception {
		Languages languages = languangesDao.getLanguageById(UUID.fromString("fd6a5902-c6c2-4de0-bfc2-fb416a4f14f"));
		System.out.println(languages);
	}

	@Test
	public void updateLanguage() {
		Languages languages = languangesDao.updateLanguage(new Languages("a49e9f9c-cf38-453e-8a16-cfd956872296", "ppppp", "qqqqqqqqq", null, null));
		System.out.println(languages);
	}

	@Test
	public void deleteLanguage() {
		int update = languangesDao.deleteLanguage(UUID.fromString("cd475cee-8a4e-469e-ba4b-ffaca3dd9fc0"));
		System.out.println(update);
	}

	@Test
	public void countLanguages() {
		int update = languangesDao.countLanguages();
		System.out.println(update);
	}

	@Test
	public void createUuid(){
		System.out.println(UUID.randomUUID());
	}

	@Test
	public void createTime(){
		System.out.println(System.currentTimeMillis());
		System.out.println(new Timestamp(System.currentTimeMillis()).toString());
	}

	@Test
	public void insertProduct(){
		Products products = new Products(UUID.randomUUID().toString(),"qqqqqq",null,null,null,new Timestamp(System.currentTimeMillis()).toString(),new Timestamp(System.currentTimeMillis()).toString(),null);
		int i = productsDao.insertProduct(products);
		System.out.println(i);
	}

	@Test
	public void getProductByName(){
		List<Products> xxxxxxxx = productsDao.getProductByName("xxxxxxxx");
		System.out.println(xxxxxxxx);
	}

	@Test
	public void deleteProduct(){
		String id = "c6c1474a-3ea3-40f8-a54e-a5bec60ebf1";
		int i = productLanguageDao.deleteProductLanguage(UUID.fromString(id));
		int i1 = productsDao.deleteProduct(UUID.fromString(id));
		System.out.println("i = " + i);
		System.out.println("i1 = " + i1);
	}

	@Test
	public void listLanguageNames(){
		List<String> stringList = productLanguageDao.listLanguageNamesByProductId("25c3d028-19a1-43a5-a0fa-ee392b36897f");
		System.out.println(stringList);

	}

	@Test
	public void listCode(){
		List<String> targetLanguagesCode = releaseprofilesDao.getTargetLanguagesCode("4551bfa6-5b65-45bd-bf8c-464fe9619914");
		System.out.println(targetLanguagesCode);
	}
}
