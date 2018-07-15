package com.app.beans;

import java.util.LinkedHashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

import com.app.entities.Comic;
import com.app.entities.Magazine;
import com.app.entities.Milk;
import com.app.entities.NewsPaper;
import com.app.entities.Novel;
import com.app.entities.OtherProducts;
import com.app.entities.TextBook;

@Getter
@Setter
public class ProductWrapper {
	private Set<NewsPaper> newsPapers = new LinkedHashSet<NewsPaper>();
	private Set<Comic> comics = new LinkedHashSet<Comic>();
	private Set<Novel> novels = new LinkedHashSet<Novel>();
	private Set<TextBook> textBooks = new LinkedHashSet<TextBook>();
	private Set<Magazine> magazines = new LinkedHashSet<Magazine>();
	private Set<Milk> milks = new LinkedHashSet<Milk>();
	private Set<OtherProducts> otherProducts = new LinkedHashSet<OtherProducts>();
}
