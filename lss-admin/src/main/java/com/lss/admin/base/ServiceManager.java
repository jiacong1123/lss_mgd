package com.lss.admin.base;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lss.admin.service.ActivityService;
import com.lss.admin.service.AdminService;
import com.lss.admin.service.BagOrderService;
import com.lss.admin.service.BannerService;
import com.lss.admin.service.ClinicService;
import com.lss.admin.service.ClueService;
import com.lss.admin.service.CommonService;
import com.lss.admin.service.DoctorService;
import com.lss.admin.service.IAdminLoginService;
import com.lss.admin.service.NewsService;
import com.lss.admin.service.PhoneService;
import com.lss.admin.service.ProductService;
import com.lss.admin.service.ScienceService;
import com.lss.admin.service.UserService;
import com.lss.admin.service.WorkOrderService;

/**
 * service 代理工厂
 * 
 * @author MMHEART
 *
 */
@Component
public class ServiceManager {

	public static AdminService adminService;

	public static ClinicService clinicService;

	public static DoctorService doctorService;

	public static UserService userService;

	public static WorkOrderService workOrderService;

	public static CommonService commonService;

	public static ActivityService activityService;

	public static ProductService productService;

	public static ScienceService scienceService;

	public static BagOrderService bagOrderService;

	public static BannerService bannerService;

	public static NewsService newsService;

	public static ClueService clueService;
	
	public static PhoneService phoneService;

	public static IAdminLoginService adminLoginService;
	
	public AdminService getAdminService() {
		return adminService;
	}

	@Resource
	public void setAdminService(AdminService adminService) {
		ServiceManager.adminService = adminService;
	}

	public ClinicService getClinicService() {
		return clinicService;
	}

	@Resource
	public void setClinicService(ClinicService clinicService) {
		ServiceManager.clinicService = clinicService;
	}

	public DoctorService getDoctorService() {
		return doctorService;
	}

	@Resource
	public void setDoctorService(DoctorService doctorService) {
		ServiceManager.doctorService = doctorService;
	}

	public UserService getUserService() {
		return userService;
	}

	@Resource
	public void setUserService(UserService userService) {
		ServiceManager.userService = userService;
	}

	public WorkOrderService getWorkOrderService() {
		return workOrderService;
	}

	@Resource
	public void setWorkOrderService(WorkOrderService workOrderService) {
		ServiceManager.workOrderService = workOrderService;
	}

	public CommonService getCommonService() {
		return commonService;
	}

	@Resource
	public void setCommonService(CommonService commonService) {
		ServiceManager.commonService = commonService;
	}

	public ActivityService getActivityService() {
		return activityService;
	}

	@Resource
	public void setActivityService(ActivityService activityService) {
		ServiceManager.activityService = activityService;
	}

	public ProductService getProductService() {
		return productService;
	}

	@Resource
	public void setProductService(ProductService productService) {
		ServiceManager.productService = productService;
	}

	public ScienceService getScienceService() {
		return scienceService;
	}

	@Resource
	public void setScienceService(ScienceService scienceService) {
		ServiceManager.scienceService = scienceService;
	}

	public BagOrderService getBagOrderService() {
		return bagOrderService;
	}

	@Resource
	public void setBagOrderService(BagOrderService bagOrderService) {
		ServiceManager.bagOrderService = bagOrderService;
	}

	public BannerService getBannerService() {
		return bannerService;
	}

	@Resource
	public void setBannerService(BannerService bannerService) {
		ServiceManager.bannerService = bannerService;
	}

	public NewsService getNewsService() {
		return newsService;
	}

	@Resource
	public void setNewsService(NewsService newsService) {
		ServiceManager.newsService = newsService;
	}

	public ClueService getClueService() {
		return clueService;
	}

	@Resource
	public void setClueService(ClueService clueService) {
		ServiceManager.clueService = clueService;
	}

	public PhoneService getPhoneService() {
		return phoneService;
	}
	
	@Resource
	public void setPhoneService(PhoneService phoneService) {
		ServiceManager.phoneService = phoneService;
	}

	public IAdminLoginService getAdminLoginService() {
		return adminLoginService;
	}
	
	@Resource
	public void setAdminLoginService(IAdminLoginService adminLoginService) {
		ServiceManager.adminLoginService = adminLoginService;
	}
	
	
}
