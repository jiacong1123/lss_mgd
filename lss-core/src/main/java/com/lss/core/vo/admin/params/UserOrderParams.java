package com.lss.core.vo.admin.params;

import com.lss.core.pojo.User;
import com.lss.core.pojo.WorkOrder;

public class UserOrderParams {
	private User user;
	private WorkOrder order;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public WorkOrder getOrder() {
		return order;
	}

	public void setOrder(WorkOrder order) {
		this.order = order;
	}
}
