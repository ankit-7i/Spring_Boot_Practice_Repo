package com.eatanyway.menu.service;

import com.eatanyway.menu.dao.CategoryDao;
import com.eatanyway.menu.dao.MenuItemDao;
import com.eatanyway.menu.model.Category;
import com.eatanyway.menu.model.MenuItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    private final CategoryDao categoryDao;
    private final MenuItemDao menuItemDao;

    public MenuService(CategoryDao categoryDao, MenuItemDao menuItemDao) {
        this.categoryDao = categoryDao;
        this.menuItemDao = menuItemDao;
    }

    public void addCategory(String name) {
        categoryDao.addCategory(name);
    }

    public void addMenuItem(MenuItem item) {
        menuItemDao.addMenuItem(item);
    }

    public List<Category> getCategories() {
        return categoryDao.findAll();
    }

    public List<MenuItem> getMenuByCategory(int categoryId) {
        return menuItemDao.findByCategory(categoryId);
    }
}

