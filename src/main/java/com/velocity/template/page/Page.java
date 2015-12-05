package com.velocity.template.page;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 描述�?
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2015�?1�?�?     608279         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 608279
 * @since 1.0
 */
public class Page<T> implements IPage<T> {

	/**  */
	private static final long serialVersionUID = 4336812407961532349L;

	// the record in page
	private int size;

	// page index
	private int index;

	// the record total
	private int total;

	private int totalRecord;

	private Collection<T> datas;

	public Page() {
	}

	public Page(Collection<T> datas, int totalRecord, int index, int size) {
		this.datas = (datas == null ? new ArrayList<T>(0) : datas);
		this.totalRecord = totalRecord;
		this.size = size;
		this.index = index;
		this.total = (totalRecord - 1) / size + 1;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getSize() {
		return size;
	}

	public int getTotal() {
		return total;
	}

	public int getIndex() {
		return index;
	}

	public Collection<T> getDatas() {
		return datas;
	}
	
}
