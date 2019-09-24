package com.lss.core.base;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lss.core.dao.ActivityMapper;
import com.lss.core.dao.AdminMapper;
import com.lss.core.dao.AdminRoleMapper;
import com.lss.core.dao.BagOrderMapper;
import com.lss.core.dao.BannerMapper;
import com.lss.core.dao.ClinicMapper;
import com.lss.core.dao.DoctorBagMapper;
import com.lss.core.dao.DoctorLoginMapper;
import com.lss.core.dao.DoctorMapper;
import com.lss.core.dao.DoctorUserMapper;
import com.lss.core.dao.IOrderPayDao;
import com.lss.core.dao.LogsMapper;
import com.lss.core.dao.NewsMapper;
import com.lss.core.dao.PicturesMapper;
import com.lss.core.dao.PopedomMapper;
import com.lss.core.dao.ProductMapper;
import com.lss.core.dao.RoleMapper;
import com.lss.core.dao.RolePopedomMapper;
import com.lss.core.dao.ScienceMapper;
import com.lss.core.dao.UserMapper;
import com.lss.core.dao.WorkOrderMapper;
import com.lss.core.dao.WorkRecordMapper;
import com.lss.core.dao.WorkTagMapper;

/**
 * dao manager
 * 
 * @author SWWH
 *
 */
@Component
public class MapperManager {
	public static AdminMapper adminMapper;
	public static AdminRoleMapper adminRoleMapper;
	public static ClinicMapper clinicMapper;
	public static DoctorMapper doctorMapper;
	public static LogsMapper logsMapper;
	public static PopedomMapper popedomMapper;
	public static RoleMapper roleMapper;
	public static RolePopedomMapper rolePopedomMapper;
	public static WorkOrderMapper workOrderMapper;
	public static WorkRecordMapper workRecordMapper;
	public static WorkTagMapper workTagMapper;
	public static UserMapper userMapper;
	public static ProductMapper productMapper;
	public static PicturesMapper picturesMapper;
	public static ActivityMapper activityMapper;
	public static ScienceMapper scienceMapper;
	public static BagOrderMapper bagOrderMapper;
	public static DoctorBagMapper doctorBagMapper;
	public static DoctorLoginMapper doctorLoginMapper;
	public static DoctorUserMapper doctorUserMapper;
	public static BannerMapper bannerMapper;
	public static NewsMapper newsMapper;
	public static IOrderPayDao orderPayDao;
	
	public AdminMapper getAdminMapper() {
		return adminMapper;
	}

	@Resource
	public void setAdminMapper(AdminMapper adminMapper) {
		MapperManager.adminMapper = adminMapper;
	}

	public AdminRoleMapper getAdminRoleMapper() {
		return adminRoleMapper;
	}

	@Resource
	public void setAdminRoleMapper(AdminRoleMapper adminRoleMapper) {
		MapperManager.adminRoleMapper = adminRoleMapper;
	}

	public ClinicMapper getClinicMapper() {
		return clinicMapper;
	}

	@Resource
	public void setClinicMapper(ClinicMapper clinicMapper) {
		MapperManager.clinicMapper = clinicMapper;
	}

	public DoctorMapper getDoctorMapper() {
		return doctorMapper;
	}

	@Resource
	public void setDoctorMapper(DoctorMapper doctorMapper) {
		MapperManager.doctorMapper = doctorMapper;
	}

	public LogsMapper getLogsMapper() {
		return logsMapper;
	}

	@Resource
	public void setLogsMapper(LogsMapper logsMapper) {
		MapperManager.logsMapper = logsMapper;
	}

	public PopedomMapper getPopedomMapper() {
		return popedomMapper;
	}

	@Resource
	public void setPopedomMapper(PopedomMapper popedomMapper) {
		MapperManager.popedomMapper = popedomMapper;
	}

	public RoleMapper getRoleMapper() {
		return roleMapper;
	}

	@Resource
	public void setRoleMapper(RoleMapper roleMapper) {
		MapperManager.roleMapper = roleMapper;
	}

	public RolePopedomMapper getRolePopedomMapper() {
		return rolePopedomMapper;
	}

	@Resource
	public void setRolePopedomMapper(RolePopedomMapper rolePopedomMapper) {
		MapperManager.rolePopedomMapper = rolePopedomMapper;
	}

	public WorkOrderMapper getWorkOrderMapper() {
		return workOrderMapper;
	}

	@Resource
	public void setWorkOrderMapper(WorkOrderMapper workOrderMapper) {
		MapperManager.workOrderMapper = workOrderMapper;
	}

	public WorkRecordMapper getWorkRecordMapper() {
		return workRecordMapper;
	}

	@Resource
	public void setWorkRecordMapper(WorkRecordMapper workRecordMapper) {
		MapperManager.workRecordMapper = workRecordMapper;
	}

	public WorkTagMapper getWorkTagMapper() {
		return workTagMapper;
	}

	@Resource
	public void setWorkTagMapper(WorkTagMapper workTagMapper) {
		MapperManager.workTagMapper = workTagMapper;
	}

	public UserMapper getUserMapper() {
		return userMapper;
	}

	@Resource
	public void setUserMapper(UserMapper userMapper) {
		MapperManager.userMapper = userMapper;
	}

	public ProductMapper getProductMapper() {
		return productMapper;
	}

	@Resource
	public void setProductMapper(ProductMapper productMapper) {
		MapperManager.productMapper = productMapper;
	}

	public PicturesMapper getPicturesMapper() {
		return picturesMapper;
	}

	@Resource
	public void setPicturesMapper(PicturesMapper picturesMapper) {
		MapperManager.picturesMapper = picturesMapper;
	}

	public ActivityMapper getActivityMapper() {
		return activityMapper;
	}

	@Resource
	public void setActivityMapper(ActivityMapper activityMapper) {
		MapperManager.activityMapper = activityMapper;
	}

	public ScienceMapper getScienceMapper() {
		return scienceMapper;
	}

	@Resource
	public void setScienceMapper(ScienceMapper scienceMapper) {
		MapperManager.scienceMapper = scienceMapper;
	}

	public BagOrderMapper getBagOrderMapper() {
		return bagOrderMapper;
	}

	@Resource
	public void setBagOrderMapper(BagOrderMapper bagOrderMapper) {
		MapperManager.bagOrderMapper = bagOrderMapper;
	}

	public DoctorBagMapper getDoctorBagMapper() {
		return doctorBagMapper;
	}

	@Resource
	public void setDoctorBagMapper(DoctorBagMapper doctorBagMapper) {
		MapperManager.doctorBagMapper = doctorBagMapper;
	}

	public DoctorLoginMapper getDoctorLoginMapper() {
		return doctorLoginMapper;
	}

	@Resource
	public void setDoctorLoginMapper(DoctorLoginMapper doctorLoginMapper) {
		MapperManager.doctorLoginMapper = doctorLoginMapper;
	}

	public DoctorUserMapper getDoctorUserMapper() {
		return doctorUserMapper;
	}

	@Resource
	public void setDoctorUserMapper(DoctorUserMapper doctorUserMapper) {
		MapperManager.doctorUserMapper = doctorUserMapper;
	}

	public BannerMapper getBannerMapper() {
		return bannerMapper;
	}

	@Resource
	public void setBannerMapper(BannerMapper bannerMapper) {
		MapperManager.bannerMapper = bannerMapper;
	}

	public NewsMapper getNewsMapper() {
		return newsMapper;
	}

	@Resource
	public void setNewsMapper(NewsMapper newsMapper) {
		MapperManager.newsMapper = newsMapper;
	}

	public IOrderPayDao getOrderPayDao() {
		return orderPayDao;
	}

	@Resource
	public void setOrderPayDao(IOrderPayDao orderPayDao) {
		MapperManager.orderPayDao = orderPayDao;
	}
	
	
}
