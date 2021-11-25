package by.ita.je.service;

import by.ita.je.exception.NotCorrectDataException;
import by.ita.je.model.Bookmarks;
import by.ita.je.model.Category;
import by.ita.je.model.Product;
import by.ita.je.model.ShoppingCart;
import by.ita.je.service.api.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class BusinessServiceImpl implements BusinessService {

    private final ProductService productService;
    private final AccountService accountService;
    private final ShoppingCartService shoppingCartService;
    private final CategoryService categoryService;
    private final BookmarksService bookmarksService;

    @Transactional(rollbackFor=Exception.class)
    @Override
    public ShoppingCart addProductToShoppingCart(Long idCart, Long idProduct) throws NotCorrectDataException {

        Product product = productService.findById(idProduct);
        ShoppingCart shoppingCart = shoppingCartService.findById(idCart);

        Collection<Product> productCollection = shoppingCart.getProducts();
        if(shoppingCart.getProducts() == null){
            shoppingCart.setProducts(List.of(product));
        } else {
            productCollection.add(product);
        }
        ShoppingCart shoppingCartWithNewProduct = shoppingCartService.update(idCart,shoppingCart);

        return shoppingCartWithNewProduct;
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public ShoppingCart addProductToShoppingCartByCurrentAccount(Long idAccount, Long idProduct) throws NotCorrectDataException {

        Product product = productService.findById(idProduct);
        ShoppingCart shoppingCart = accountService.findById(idAccount).getShoppingCart();

        Collection<Product> productCollection = shoppingCart.getProducts();
        if(shoppingCart.getProducts() == null){
            shoppingCart.setProducts(List.of(product));
        } else {
            productCollection.add(product);
        }
        ShoppingCart shoppingCartWithNewProduct = shoppingCartService
                .update(accountService.findById(idAccount).getShoppingCart().getId(), shoppingCart);

        return shoppingCartWithNewProduct;
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public Bookmarks addProductToBookmarksByCurrentAccount(Long idAccount, Long idProduct) throws NotCorrectDataException {

        Product product = productService.findById(idProduct);
        Bookmarks bookmarks = accountService.findById(idAccount).getBookmarks();

        Collection<Product> productCollection = bookmarks.getProducts();
        if(bookmarks.getProducts() == null){
            bookmarks.setProducts(List.of(product));
        } else {
            productCollection.add(product);
        }
        Bookmarks bookmarksWithNewProduct = bookmarksService
                .update(accountService.findById(idAccount).getBookmarks().getId(), bookmarks);

        return bookmarksWithNewProduct;
    }

    @Override
    public List<Product> findAllProductsInShoppingCart(Long idCart) {
        final Spliterator<Product> result = shoppingCartService.findById(idCart).getProducts().spliterator();
        return StreamSupport
                .stream(result,false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findAllProductsInShoppingCartByCurrentAccount(Long idAccount) {
        final Spliterator<Product> result = accountService.findById(idAccount).getShoppingCart().getProducts().spliterator();
        return StreamSupport
                .stream(result,false)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Product> getProductsByCategory(Long id) {
        final Spliterator<Product> result = categoryService.findById(id).getProducts().spliterator();
        return StreamSupport
                .stream(result,false)
                .collect(Collectors.toList());
    }

    @Override
    public Category getCategoryByProduct(Long id) {
        Category category = productService.findById(id).getCategory();
        return category;
    }

}

