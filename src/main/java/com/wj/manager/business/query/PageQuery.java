package com.wj.manager.business.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageQuery<T> extends Page<T> {
    private Object keyword;
    private Object keyword1;
    private Object keyword2;
    private Object keyword3;

}
