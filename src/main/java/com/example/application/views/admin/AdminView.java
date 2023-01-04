package com.example.application.views.admin;

import com.example.application.data.entity.Users;
import com.example.application.data.service.UserService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.security.PermitAll;

@Component
@Scope("prototype")
@Route(value="admin", layout = MainLayout.class)
@PageTitle("Администрирование")
@PermitAll
public class AdminView extends VerticalLayout {
    Grid<Users> grid = new Grid<>(Users.class);
    TextField filterText = new TextField();
    UserForm form;
    UserService service;

    public AdminView(UserService service) {
        this.service = service;
        addClassName("admin-view");
        setSizeFull();
        configureGrid();

        form = new UserForm(service.findAllRoles());
        form.setWidth("25em");
        form.addListener(UserForm.SaveEvent.class, this::saveUser);
        form.addListener(UserForm.DeleteEvent.class, this::deleteUser);
        form.addListener(UserForm.CloseEvent.class, e -> closeEditor());

        FlexLayout content = new FlexLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.setFlexShrink(0, form);
        content.addClassNames("content", "gap-m");
        content.setSizeFull();

        add(getToolbar(), content);
        updateList();
        closeEditor();
        grid.asSingleSelect().addValueChangeListener(event ->
                editUser(event.getValue()));
    }

    private void configureGrid() {
        grid.addClassNames("user-grid");
        grid.setSizeFull();
        grid.setColumns("login", "password", "role");

        grid.addColumn(Users::getLogin).setHeader("Логин").setSortable(true);
        grid.addColumn(Users::getPassword).setHeader("Пароль");
        grid.addColumn(user -> user.getUserRole().getRole()).setHeader("Роль").setSortable(true);
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Фильтр по логину...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addUserButton = new Button("Создать пользователя");
        addUserButton.addClickListener(click -> addUser());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addUserButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void saveUser(UserForm.SaveEvent event) {
        service.saveUser(event.getUser());
        updateList();
        closeEditor();
    }

    private void deleteUser(UserForm.DeleteEvent event) {
        service.deleteUser(event.getUser());
        updateList();
        closeEditor();
    }

    public void editUser(Users user) {
        if (user == null) {
            closeEditor();
        } else {
            form.setUser(user);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    void addUser() {
        grid.asSingleSelect().clear();
        editUser(new Users());
    }

    private void closeEditor() {
        form.setUser(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(service.findAllUsers(filterText.getValue()));
    }
}
