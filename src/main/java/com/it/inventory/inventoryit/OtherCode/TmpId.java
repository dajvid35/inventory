package com.it.inventory.inventoryit.OtherCode;

import org.springframework.stereotype.Component;

@Component
public class TmpId {
	private int tmpId;

	public TmpId() {
		
	}

	public TmpId(int tmpId) {
		this.tmpId = tmpId;
	}

	public int getTmpId() {
		return tmpId;
	}

	public void setTmpId(int tmpId) {
		this.tmpId = tmpId;
	}

	@Override
	public String toString() {
		return "TmpId [tmpId=" + tmpId + "]";
	}
	
	
	
}
