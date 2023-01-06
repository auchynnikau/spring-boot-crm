package com.example.application.views;

import com.example.application.security.SecurityService;
import com.example.application.views.admin.AdminView;
import com.example.application.views.list.ListView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {
    private final SecurityService securityService;

    public MainLayout(SecurityService securityService) {
        this.securityService = securityService;
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Spring Boot CRM");
        logo.addClassNames("text-l", "m-m");
        Button logout = new Button("Logout", e -> securityService.logout());
        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, logout);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidth("100%");
        header.addClassNames("py-0", "px-m");
        addToNavbar(header);
    }

    private void createDrawer() {
        RouterLink analyticsLink = new RouterLink("Analytics", DashboardView.class);
        RouterLink adminLink = new RouterLink("Admin page", AdminView.class);
        RouterLink listLink = new RouterLink("Deals", ListView.class);

        listLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(listLink, analyticsLink, adminLink));
    }
}
