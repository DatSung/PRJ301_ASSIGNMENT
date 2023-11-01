/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author 84859
 */
public interface IMyConstant {

    //các đường dẫn
    public static final String PAGE_WELCOME= "welcome.jsp";
    public static final String PAGE_LOGIN = "page_login.jsp";
    public static final String PAGE_REGISTER = "page_register.jsp";
    public static final String PAGE_SERVICES = "page_services.jsp";
    public static final String PAGE_CONTRACT = "page_contract.jsp";
    public static final String PAGE_REGISTER_SERVICE = "page_register_service.jsp";
    public static final String PAGE_SETTING = "page_setting.jsp";
    public static final String PAGE_SUPPORT = "page_support.jsp";
    public static final String PAGE_EDIT = "page_edit.jsp";
    public static final String PAGE_ERROR = "error.html";

    //các servlet name
    public static final String CONTROLLER_DISPATCHER = "DispatcherServlet";
    public static final String CONTROLLER_LOGIN = "LoginServlet";
    public static final String CONTROLLER_REGISTER_SERVICE = "RegisterServiceServlet";
    public static final String CONTROLLER_EDIT = "EditServlet";
    public static final String CONTROLLER_REGISTER = "RegisterServlet";
    public static final String CONTROLLER_SUPPORT = "SupportServlet";
    public static final String CONTROLLER_DELETE_REQUEST = "DeleteRequestServlet";

    //các action dùng trong dispatcher Servlet
    public static final String ACTION_PAGE_NOTHING = "";
    public static final String ACTION_PAGE_REGISTER = "registerpage";
    public static final String ACTION_PAGE_SERVICE = "servicespage";
    public static final String ACTION_PAGE_CONTRACT = "contractpage";
    public static final String ACTION_PAGE_SETTING = "settingpage";
    public static final String ACTION_PAGE_SUPPORT = "supportpage";
    public static final String ACTION_PAGE_REGISTER_SERVICE = "registerservicepage";
    public static final String ACTION_PAGE_EDIT = "editpage";
    public static final String ACTION_PAGE_WELCOME = "welcomepage";

    public static final String ACTION_CONTROLLER_LOGIN = "login";
    public static final String ACTION_CONTROLLER_REGISTER_SERVICE = "registerservice";
    public static final String ACTION_CONTROLLER_EDIT = "edit";
    public static final String ACTION_CONTROLLER_REGISTER = "register";
    public static final String ACTION_CONTROLLER_SUPPORT = "support";
    public static final String ACTION_CONTROLLER_DELETE_REQUEST = "deleterequest";

    //    FOR AD 
    public static final String AD_CONTROLLER_DISPATCHER = "AdminDispatcherServlet";
    //    FOR VIEW STAFF 
    public static final String AD_CONTROLLER_STAFF = "AdminViewStaffServlet";
    public static final String AD_ACTION_CONTROLLER_STAFF = "adviewstaff";
    public static final String AD_ACTION_PAGE_STAFF = "adpagestaff";
    public static final String AD_PAGE_STAFF = "ad_page_staff.jsp";
    //    FOR VIEW CUSTOMER
    public static final String AD_CONTROLLER_CUSTOMER = "AdminViewCustomerServlet";
    public static final String AD_ACTION_CONTROLLER_CUSTOMER = "adviewcustomer";
    public static final String AD_ACTION_PAGE_CUSTOMER = "adpagecustomer";
    public static final String AD_PAGE_CUSTOMER = "ad_page_customer.jsp";
    //    FOR VIEW REQUEST
    public static final String AD_CONTROLLER_REQUEST = "AdminViewRequestServlet";
    public static final String AD_ACTION_CONTROLLER_REQUEST = "adviewrequest";
    public static final String AD_ACTION_PAGE_REQUEST = "adpagerequest";
    public static final String AD_PAGE_REQUEST = "ad_page_request.jsp";
    //    FOR VIEW SERVICE
    public static final String AD_CONTROLLER_SERVICE = "AdminViewServiceServlet";
    public static final String AD_ACTION_CONTROLLER_SERVICE = "adviewservice";
    public static final String AD_ACTION_PAGE_SERVICE = "adpageservice";
    public static final String AD_PAGE_SERVICE = "ad_page_service.jsp";
    //    FOR VIEW SETTING
    public static final String AD_CONTROLLER_SETTING = "AdminViewSettingServlet";
    public static final String AD_ACTION_CONTROLLER_SETTING = "adviewsetting";
    public static final String AD_ACTION_PAGE_SETTING = "adpagesetting";
    public static final String AD_PAGE_SETTING = "ad_page_setting.jsp";

}
