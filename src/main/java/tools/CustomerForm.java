package tools;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import elemental.events.KeyboardEvent;
import model.Customer;
import service.CustomerService;
import vaadin.MyUI;

/**
 * Created by VMoskalik on 10.05.2016.
 */
public class CustomerForm extends FormLayout {
    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private TextField email = new TextField("Email");
    private NativeSelect status = new NativeSelect("Status");
    private PopupDateField birthdate = new PopupDateField("Birthday");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");

    private CustomerService service = CustomerService.getInstance();
    private Customer customer;
    private MyUI myUI;

    public CustomerForm(MyUI myUI) {
        this.myUI = myUI;
        status.addItems(CustomerStatus.values());

        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(KeyboardEvent.KeyCode.ENTER);
        delete.setStyleName(ValoTheme.BUTTON_DANGER);

        setSizeUndefined();
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        buttons.setSpacing(true);
        addComponents(firstName, lastName, email, status, birthdate, buttons);
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
        BeanFieldGroup.bindFieldsUnbuffered(customer, this);

        save.addClickListener(event -> this.save());
        delete.addClickListener(event -> this.delete());

        // Show delete button for only customers already in the database
        delete.setVisible(customer.isPersisted());
        setVisible(true);
        firstName.selectAll();
    }

    private void delete(){
        service.delete(customer);
        myUI.updateList();
        setVisible(false);
    }

    private void save(){
        service.save(customer);
        myUI.updateList();
        setVisible(false);
    }
}
