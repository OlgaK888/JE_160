package je.service;

import je.exception.NotCorrectDataException;
import je.model.Account;
import je.model.Product;
import je.model.ShoppingCart;
import je.service.api.AccountService;
import je.service.api.BusinessService;
import je.service.api.ProductService;
import je.service.api.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Objects;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class BusinessServiceImpl implements BusinessService {

    private final ProductService productService;
    private final AccountService accountService;
    private final ShoppingCartService shoppingCartService;


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
    public ShoppingCart addProductToShoppingCart(Long idProduct, Long idCart) throws NotCorrectDataException {

        Product product = productService.findById(idProduct);
        if(shoppingCartService.findById(idCart) == null){
            shoppingCartService.create(ShoppingCart.builder().build());
        }
        ShoppingCart updateShoppingCart = shoppingCartService.findById(idCart);
        Collection<Product> productCollection =updateShoppingCart.getProducts();
        productCollection.add(product);
        System.out.println("11  " + updateShoppingCart.getProducts());

        return updateShoppingCart;
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


