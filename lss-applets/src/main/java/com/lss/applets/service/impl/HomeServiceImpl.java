package com.lss.applets.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lss.applets.service.HomeService;
import com.lss.core.base.MapperManager;
import com.lss.core.constant.ResponseCode;
import com.lss.core.pojo.Activity;
import com.lss.core.pojo.Product;
import com.lss.core.util.FormatUtil;
import com.lss.core.util.ObjectUtil;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.DoctorVo;
import com.lss.core.vo.admin.ProductVo;
import com.lss.core.vo.admin.params.ClinicParams;
import com.lss.core.vo.admin.params.DoctorParams;
import com.lss.core.vo.admin.params.ScienceParams;

@Service
public class HomeServiceImpl implements HomeService {

	@Override
	public ReturnVo doctorlist(DoctorParams params) {
		ReturnVo result = new ReturnVo();
		result.setPage(params.getPage());
		result.setLimit(params.getLimit());
		params.setStatus(1);
		result.setTotal(MapperManager.doctorMapper.doctorCount(params));
		List<DoctorVo> list = MapperManager.doctorMapper.doctorList(params);
		if (!list.isEmpty()) {
			for (DoctorVo doctor : list) {
				if (ObjectUtil.isNotEmpty(doctor.getPhone())) {
					doctor.setPhone(FormatUtil.formatMobile(doctor.getPhone()));
				}
			}
		}
		result.setObj(list);
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public ReturnVo cliniclist(ClinicParams params) {
		ReturnVo result = new ReturnVo();
		result.setPage(params.getPage());
		result.setLimit(params.getLimit());
		params.setStatus(1);
		result.setTotal(MapperManager.clinicMapper.clinicCount(params));
		result.setObj(MapperManager.clinicMapper.clinicList(params));
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public ReturnVo sciencelist(ScienceParams params) {
		ReturnVo result = new ReturnVo();
		result.setPage(params.getPage());
		result.setLimit(params.getLimit());
		params.setStatus(1);
		result.setTotal(MapperManager.scienceMapper.adminCount(params));
		result.setObj(MapperManager.scienceMapper.adminList(params));
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public ReturnVo activitylist(ScienceParams params) {
		ReturnVo result = new ReturnVo();
		result.setPage(params.getPage());
		result.setLimit(params.getLimit());
		params.setStatus(1);
		result.setTotal(MapperManager.activityMapper.adminCount(params));
		result.setObj(MapperManager.activityMapper.adminList(params));
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public ReturnVo activitydetails(Activity params) {
		ReturnVo result = new ReturnVo();
		result.setObj(MapperManager.activityMapper.selectByPrimaryKey(params
				.getActid()));
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public ReturnVo productlist(Product params) {
		ReturnVo result = new ReturnVo();
		if (params.getClassid() == null) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg(ResponseCode.parameterErrorMsg);
			return result;
		}
		result.setObj(MapperManager.productMapper.getListByClassid(params
				.getClassid()));
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public ReturnVo productdetails(Product params) {
		ReturnVo result = new ReturnVo();
		if (params.getPid() == null) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg(ResponseCode.parameterErrorMsg);
			return result;
		}
		Product pro = MapperManager.productMapper.selectByPrimaryKey(params
				.getPid());
		if (pro != null) {
			ProductVo vo = new ProductVo();
			vo.setPid(pro.getPid());
			vo.setClassid(pro.getClassid());
			vo.setTitle(pro.getTitle());
			vo.setDes(pro.getDes());
			vo.setImage(pro.getImage());
			vo.setCostprice(pro.getCostprice());
			vo.setPrice(pro.getPrice());
			vo.setDetails(pro.getDetails());
			vo.setStatus(pro.getStatus());
			vo.setCreatetime(pro.getCreatetime());
			// 查询产品图片
			vo.setList(MapperManager.picturesMapper.getList(pro.getPid()));
			result.setObj(vo);
		}
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}
}
