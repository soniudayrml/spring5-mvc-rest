package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class bootstrap implements CommandLineRunner {
    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;
    private VendorRepository vendorRepository;

    public bootstrap(CustomerRepository customerRepository,CategoryRepository categoryRepository,VendorRepository vendorRepository) {
        this.customerRepository=customerRepository;
        this.categoryRepository = categoryRepository;
        this.vendorRepository=vendorRepository;

    }

    @Override
    public void run(String... args) throws Exception {
        Category fruits=new Category();
        fruits.setName("FRUITS");
        categoryRepository.save(fruits);
        Category dried=new Category();
        dried.setName("DRIED");
        categoryRepository.save(dried);
        Category fresh=new Category();
        fresh.setName("FRESH");
        categoryRepository.save(fresh);
        Category exotic=new Category();
        exotic.setName("EXOTIC");
        categoryRepository.save(exotic);
        Category nuts=new Category();
        nuts.setName("NUTS");
        categoryRepository.save(nuts);
        System.out.println("Loading Data.... No. of Categories="+categoryRepository.count());
        Customer aman=new Customer();
        aman.setFirstName("Aman");
        aman.setLastName("Jain");
        customerRepository.save(aman);
        Customer naveen=new Customer();
        naveen.setFirstName("Naveen");
        naveen.setLastName("Soni");
        customerRepository.save(naveen);
        Customer suresh=new Customer();
        suresh.setFirstName("Suresh");
        suresh.setLastName("Jen");
        customerRepository.save(suresh);
        System.out.println("Loading Data.... No. of Customers="+customerRepository.count());

        Vendor rahul=new Vendor();
        rahul.setName("Rahul");
        vendorRepository.save(rahul);
        Vendor ajay=new Vendor();
        ajay.setName("Ajay");
        vendorRepository.save(ajay);
        Vendor vijay=new Vendor();
        vijay.setName("Vijay");
        vendorRepository.save(vijay);
        Vendor naman=new Vendor();
        naman.setName("Naman");
        vendorRepository.save(naman);
        System.out.println("Loading Data.... No. of Vendors="+vendorRepository.count());



    }
}
