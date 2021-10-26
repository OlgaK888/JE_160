package by.ita.je.service;

import by.ita.je.exception.NotCorrectDataException;
import by.ita.je.model.Account;
import by.ita.je.model.Product;
import by.ita.je.service.api.*;
import by.ita.je.model.ShoppingCart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Override
    public Account findById(Long idAccount) {
        Account account = accountService.findById(idAccount);
        System.out.println(account);
        return account;
    }

    @Override
    public Product getProductToShop(Long idProduct) {
        Product product = productService.findById(idProduct);
        System.out.println(product);
        return product;
    }

    @Transactional
    @Override
    public ShoppingCart addProductToShoppingCart(Long idCart, Long idProduct) throws NotCorrectDataException {

        Product product = productService.findById(idProduct);
        ShoppingCart shoppingCart = shoppingCartService.findById(idCart);
        /*if(shoppingCartService.findById(idCart) == null){
            shoppingCartService.create(ShoppingCart.builder().build());
        }*/
        Collection<Product> productCollection = shoppingCart.getProducts();
        if(shoppingCart.getProducts() == null){
            shoppingCart.setProducts(List.of(product));
        } else {
            productCollection.add(product);
        }
        ShoppingCart shoppingCartWithNewProduct = shoppingCartService.update(shoppingCart);
        System.out.println("11  " + shoppingCart.getProducts());
        System.out.println("22  " + shoppingCart);

        return shoppingCartWithNewProduct;
    }

    @Override
    public Collection<Product> findAllProductsInShoppingCart(Long idCart) {
        final Spliterator<Product> result = shoppingCartService.findById(idCart).getProducts().spliterator();
        return StreamSupport
                .stream(result,false)
                .collect(Collectors.toList());
    }

    /*@Override
    public Collection<Product> findProductsByCategory(String categoryName) {
        final Spliterator<Product> result = categoryService.findByName(categoryName).getProducts().spliterator();
        return StreamSupport
                .stream(result,false)
                .collect(Collectors.toList());
    }*/

    @Override
    public Collection<Product> getProductsByCategory(String id) {
        final Spliterator<Product> result = categoryService.findById(Long.valueOf(id)).getProducts().spliterator();
        return StreamSupport
                .stream(result,false)
                .collect(Collectors.toList());
    }

    /*@Transactional
    @Override
    public Account addProductToShoppingCart(long idAccount, long idProduct) throws NotCorrectDataException {
        Account account = accountService.findById(idAccount);
        System.out.println(account);
        //ArrayList<Product> cart = new ArrayList<>();
        account.getShoppingCart();
        if (account.getShoppingCart() == null){
            account.setShoppingCart(new ShoppingCart());
            //ShoppingCart.builder().build().setAccount(account);
            //ShoppingCart.builder().build().setProducts((Collection<Product>) productService.findById(idProduct));
            account.getShoppingCart().getId();
        }
        System.out.println("11  " + account.getShoppingCart().getId());
        Product product = productService.findById(idProduct);
        //account.getShoppingCart().getProducts().forEach(product1 -> productService.findById(idProduct));
        Collection<Product> productCollection = account.getShoppingCart().getProducts();
        productCollection.add(product);
        System.out.println("22  " + account.getShoppingCart().getProducts());
        //for (int i = 1; i <= number; i++) {
            //cart.add(product);
            //account.getShoppingCart().getProducts().forEach(product1->productService.findById(idProduct));

       if (Objects.nonNull(researchProject.getTeacher().getStudents())) {
            researchProject.getTeacher().getStudents().forEach(student ->{if (student.getId()>0)
                studentService.update(student.getId(), student);
            });

        return account;
    }*/

    /*@Transactional
    @Override
    public Account addProductsToShoppingCart(long idAccount, long idProduct) throws NotCorrectDataException {
        Account account = accountService.findById(idAccount);
        /*if (account == null) throw new NotCorrectDataException("There is no account with id = " +
                idAccount + " in database");
        //ArrayList<Product> cart = new ArrayList<>();
        //account.getShoppingCart();
        Product product = productService.findById(idProduct);
        if (product == null) throw new NotCorrectDataException("There is no product with id = " +
                idProduct + " in database");
        //for (int i = 1; i <= number; i++) {
            //cart.add(product);
            account.getShoppingCart().add(product);

        return account;
    }*/
}

    /*@Transactional
    @Override
    public ResearchProject update(Long id, ResearchProject researchProject) {

        if(Objects.nonNull(researchProject.getLiterarySources())) {
            researchProject.getLiterarySources().forEach(literarySource -> {
                if (literarySource.getId() > 0)
                    literarySourceService.update(literarySource.getId(), literarySource);
            });
        }

        if (Objects.nonNull(researchProject.getTeacher()) && researchProject.getTeacher().getId()>0) {
            teacherService.update(researchProject.getTeacher().getId(), researchProject.getTeacher());
        }
        if (Objects.nonNull(researchProject.getTeacher().getStudents())) {
            researchProject.getTeacher().getStudents().forEach(student ->{if (student.getId()>0)
                studentService.update(student.getId(), student);
            });
        }
        return researchProjectService.update(id, researchProject);
    }*/


