package com.app.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.beans.ProductWrapper;
import com.app.entities.Comic;
import com.app.entities.DateWiseRates;
import com.app.entities.DayWiseRates;
import com.app.entities.Distributor;
import com.app.entities.Magazine;
import com.app.entities.Milk;
import com.app.entities.MonthWiseRates;
import com.app.entities.NewsPaper;
import com.app.entities.Novel;
import com.app.entities.OtherProducts;
import com.app.entities.Product;
import com.app.entities.TextBook;
import com.app.enums.ProductType;
import com.app.exceptions.ResourceNotFoundException;
import com.app.exceptions.UnsupportedProductException;
import com.app.repositories.ComicRepository;
import com.app.repositories.MagazineRepository;
import com.app.repositories.MilkRepository;
import com.app.repositories.NewsPaperRepository;
import com.app.repositories.NovelRepository;
import com.app.repositories.OtherProductsRepository;
import com.app.repositories.TextBookRepository;

@RestController
@RequestMapping("/api")
@Api
public class ProductController {

	@Autowired
	private NewsPaperRepository newsPaperRepository;

	@Autowired
	private MilkRepository milkRepository;

	@Autowired
	private MagazineRepository magazineRepository;

	@Autowired
	private ComicRepository comicRepository;

	@Autowired
	private NovelRepository novelRepository;

	@Autowired
	private TextBookRepository textBookRepository;

	@Autowired
	private OtherProductsRepository otherProductsRepository;

	@GetMapping("/products")
	@ApiOperation(value = "View all products")
	public List<? extends Product> getAllProducts() {
		List<Product> products = new ArrayList<Product>();
		products.addAll(comicRepository.findAll());
		products.addAll(magazineRepository.findAll());
		products.addAll(newsPaperRepository.findAll());
		products.addAll(novelRepository.findAll());
		products.addAll(textBookRepository.findAll());
		products.addAll(milkRepository.findAll());
		products.addAll(otherProductsRepository.findAll());
		return products;
	}

	@GetMapping("/products/{productType}")
	@ApiOperation(value = "View all products by productType")
	public List<? extends Product> getAllByProductType(
			@PathVariable(value = "productType") ProductType productType) {
		switch (productType) {
		case COMIC:
			return comicRepository.findAll();
		case MAGAZINE:
			return magazineRepository.findAll();
		case MILK:
			return milkRepository.findAll();
		case NEWSPAPER:
			return newsPaperRepository.findAll();
		case NOVEL:
			return novelRepository.findAll();
		case TEXTBOOK:
			return textBookRepository.findAll();
		case OTHERS:
			return otherProductsRepository.findAll();
		default:
			throw new UnsupportedProductException();
		}
	}

	@GetMapping("/products/{productType}/{id}")
	@ApiOperation(value = "View product by productType and id")
	public Product getProductById(
			@PathVariable(value = "productType") ProductType productType,
			@PathVariable(value = "id") Long id) {
		switch (productType) {
		case COMIC:
			return comicRepository.findById(id).orElseThrow(
					() -> new ResourceNotFoundException("Comic", "id", id));
		case MAGAZINE:
			return magazineRepository.findById(id).orElseThrow(
					() -> new ResourceNotFoundException("Magazine", "id", id));
		case MILK:
			return milkRepository.findById(id).orElseThrow(
					() -> new ResourceNotFoundException("Milk", "id", id));
		case NEWSPAPER:
			return newsPaperRepository.findById(id).orElseThrow(
					() -> new ResourceNotFoundException("NewsPaper", "id", id));
		case NOVEL:
			return novelRepository.findById(id).orElseThrow(
					() -> new ResourceNotFoundException("Novel", "id", id));
		case TEXTBOOK:
			return textBookRepository.findById(id).orElseThrow(
					() -> new ResourceNotFoundException("TextBook", "id", id));
		case OTHERS:
			return otherProductsRepository.findById(id).orElseThrow(
					() -> new ResourceNotFoundException("OtherProducts", "id",
							id));
		default:
			throw new UnsupportedProductException();
		}
	}

	@PostMapping("/products/")
	@ApiOperation(value = "Create multiple products")
	public void createProducts(
			@RequestParam(value = "type") ProductType productType,
			@Valid @RequestBody ProductWrapper products) {
		saveProducts(productType, products);
	}

	private void saveProducts(ProductType productType, ProductWrapper products) {

		Set<Comic> comics = products.getComics();
		if (!CollectionUtils.isEmpty(comics)) {
			comicRepository.saveAll(comics);
		}

		Set<NewsPaper> newsPapers = products.getNewsPapers();
		if (!CollectionUtils.isEmpty(newsPapers)) {
			newsPaperRepository.saveAll(newsPapers);
		}

		Set<Novel> novels = products.getNovels();
		if (!CollectionUtils.isEmpty(novels)) {
			novelRepository.saveAll(novels);
		}

		Set<TextBook> textBooks = products.getTextBooks();
		if (!CollectionUtils.isEmpty(textBooks)) {
			textBookRepository.saveAll(textBooks);
		}

		Set<Magazine> magazines = products.getMagazines();
		if (!CollectionUtils.isEmpty(magazines)) {
			magazineRepository.saveAll(magazines);
		}

		Set<Milk> milks = products.getMilks();
		if (!CollectionUtils.isEmpty(milks)) {
			milkRepository.saveAll(milks);
		}

		Set<OtherProducts> otherProducts = products.getOtherProducts();
		if (!CollectionUtils.isEmpty(otherProducts)) {
			otherProductsRepository.saveAll(otherProducts);
		}
	}

	@PutMapping("/products/{productType}/{id}")
	@ApiOperation(value = "Update product by productType and id")
	public Product updateProduct(
			@PathVariable(value = "productType") ProductType productType,
			@PathVariable(value = "id") Long id,
			@Valid @RequestBody Product productDetails) {

		Product product = null;

		switch (productType) {
		case COMIC:
			Comic comic = comicRepository.findById(id).orElseThrow(
					() -> new ResourceNotFoundException("Comic", "id", id));
			Comic comicDetails = (Comic) productDetails;
			comic.setAuthor(comicDetails.getAuthor());
			comic.setLanguage(comicDetails.getLanguage());
			comic.setPublications(comicDetails.getPublications());
			comic.setPublishedOn(comicDetails.getPublishedOn());
			product = comic;
			break;
		case MAGAZINE:
			Magazine magazine = magazineRepository.findById(id).orElseThrow(
					() -> new ResourceNotFoundException("Magazine", "id", id));
			Magazine magazineDetails = (Magazine) productDetails;
			magazine.setLanguage(magazineDetails.getLanguage());
			magazine.setScheduleInDays(magazineDetails.getScheduleInDays());
			product = magazine;
			break;
		case MILK:
			Milk milk = milkRepository.findById(id).orElseThrow(
					() -> new ResourceNotFoundException("Milk", "id", id));
			Milk milkDetails = (Milk) productDetails;
			milk.setQuantity(milkDetails.getQuantity());
			product = milk;
			break;
		case NEWSPAPER:
			NewsPaper newsPaper = newsPaperRepository.findById(id).orElseThrow(
					() -> new ResourceNotFoundException("NewsPaper", "id", id));
			NewsPaper newsPaperDetails = (NewsPaper) productDetails;
			newsPaper.setLanguage(newsPaperDetails.getLanguage());
			product = newsPaper;
			break;
		case NOVEL:
			Novel novel = novelRepository.findById(id).orElseThrow(
					() -> new ResourceNotFoundException("Novel", "id", id));
			Novel novelDetails = (Novel) productDetails;
			novel.setLanguage(novelDetails.getLanguage());
			novel.setAuthor(novelDetails.getAuthor());
			novel.setPublications(novelDetails.getPublications());
			novel.setPublishedOn(novelDetails.getPublishedOn());
			novel.setGenre(novelDetails.getGenre());
			product = novel;
			break;
		case TEXTBOOK:
			TextBook textBook = textBookRepository.findById(id).orElseThrow(
					() -> new ResourceNotFoundException("TextBook", "id", id));
			TextBook textBookDetails = (TextBook) productDetails;
			textBook.setLanguage(textBookDetails.getLanguage());
			textBook.setAuthor(textBookDetails.getAuthor());
			textBook.setPublications(textBookDetails.getPublications());
			textBook.setPublishedOn(textBookDetails.getPublishedOn());
			product = textBook;
			break;
		case OTHERS:
			OtherProducts otherProducts = otherProductsRepository.findById(id)
					.orElseThrow(
							() -> new ResourceNotFoundException(
									"OtherProducts", "id", id));
			OtherProducts otherProductsDetails = (OtherProducts) productDetails;
			otherProducts.setAttributes(otherProductsDetails.getAttributes());
			product = otherProducts;
			break;
		default:
			throw new UnsupportedProductException();
		}

		String name = productDetails.getName();
		List<Distributor> distributors = productDetails.getDistributors();
		String priceCurrency = productDetails.getPriceCurrency();
		Double deliveryCharge = productDetails.getDeliveryCharge();
		DayWiseRates dayWiseRates = productDetails.getDayWiseRates();
		DateWiseRates dateWiseRates = productDetails.getDateWiseRates();
		MonthWiseRates monthWiseRates = productDetails.getMonthWiseRates();

		if (!StringUtils.isEmpty(name)) {
			product.setName(name);
		}

		if (!StringUtils.isEmpty(priceCurrency)) {
			product.setPriceCurrency(priceCurrency);
		}

		if (distributors != null) {
			product.setDistributors(distributors);
		}

		if (deliveryCharge == null) {
			product.setDeliveryCharge(0.0);
		}

		product.setType(productType);
		product.setDayWiseRates(dayWiseRates);
		product.setDateWiseRates(dateWiseRates);
		product.setMonthWiseRates(monthWiseRates);

		return null;//TODO- Implement
	}

	@DeleteMapping("/products/{productType}/{id}")
	@ApiOperation(value = "Delete product by productType and id")
	public ResponseEntity<?> deleteProduct(
			@PathVariable(value = "productType") ProductType productType,
			@PathVariable(value = "id") Long id) {
		switch (productType) {
		case COMIC:
			Comic comic = comicRepository.findById(id).orElseThrow(
					() -> new ResourceNotFoundException("Comic", "id", id));
			comicRepository.delete(comic);
			break;
		case MAGAZINE:
			Magazine magazine = magazineRepository.findById(id).orElseThrow(
					() -> new ResourceNotFoundException("Magazine", "id", id));
			magazineRepository.delete(magazine);
			break;
		case MILK:
			Milk milk = milkRepository.findById(id).orElseThrow(
					() -> new ResourceNotFoundException("Milk", "id", id));
			milkRepository.delete(milk);
			break;
		case NEWSPAPER:
			NewsPaper newsPaper = newsPaperRepository.findById(id).orElseThrow(
					() -> new ResourceNotFoundException("NewsPaper", "id", id));
			newsPaperRepository.delete(newsPaper);
			break;
		case NOVEL:
			Novel novel = novelRepository.findById(id).orElseThrow(
					() -> new ResourceNotFoundException("Novel", "id", id));
			novelRepository.delete(novel);
			break;
		case TEXTBOOK:
			TextBook textBook = textBookRepository.findById(id).orElseThrow(
					() -> new ResourceNotFoundException("TextBook", "id", id));
			textBookRepository.delete(textBook);
			break;
		case OTHERS:
			OtherProducts otherProducts = otherProductsRepository.findById(id)
					.orElseThrow(
							() -> new ResourceNotFoundException(
									"OtherProducts", "id", id));
			otherProductsRepository.delete(otherProducts);
			break;
		default:
			throw new UnsupportedProductException();
		}

		return ResponseEntity.ok().build();
	}
}
