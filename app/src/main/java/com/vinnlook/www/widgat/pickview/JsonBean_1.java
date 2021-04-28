package com.vinnlook.www.widgat.pickview;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.Collection;
import java.util.List;

/**
 * @Description:
 * @Time:2020/4/29$
 * @Author:pk$
 */
public class JsonBean_1 implements IPickerViewData {

    @Override
    public String getPickerViewText() {
        return name;
    }
    /**
     * name : 北京
     * id : 2
     * parent_id : 1
     * c_list : [{"name":"北京市","id":"37","parent_id":"2","d_list":[{"name":"东城区","id":"403","parent_id":"37"},{"name":"西城区","id":"404","parent_id":"37"},{"name":"崇文区","id":"405","parent_id":"37"},{"name":"宣武区","id":"406","parent_id":"37"},{"name":"朝阳区","id":"407","parent_id":"37"},{"name":"丰台区","id":"408","parent_id":"37"},{"name":"石景山区","id":"409","parent_id":"37"},{"name":"海淀区","id":"410","parent_id":"37"},{"name":"门头沟区","id":"411","parent_id":"37"},{"name":"房山区","id":"412","parent_id":"37"},{"name":"通州区","id":"413","parent_id":"37"},{"name":"顺义区","id":"414","parent_id":"37"},{"name":"昌平区","id":"415","parent_id":"37"},{"name":"大兴区","id":"416","parent_id":"37"},{"name":"怀柔区","id":"417","parent_id":"37"},{"name":"平谷区","id":"418","parent_id":"37"},{"name":"密云县","id":"419","parent_id":"37"},{"name":"延庆县","id":"420","parent_id":"37"},{"name":"其它区","id":"421","parent_id":"37"}]}]
     */

    private String name;
    private String id;
    private String parent_id;
    private List<CListBean> c_list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public List<CListBean> getC_list() {
        return c_list;
    }

    public void setC_list(List<CListBean> c_list) {
        this.c_list = c_list;
    }

    public static class CListBean {
        /**
         * name : 北京市
         * id : 37
         * parent_id : 2
         * d_list : [{"name":"东城区","id":"403","parent_id":"37"},{"name":"西城区","id":"404","parent_id":"37"},{"name":"崇文区","id":"405","parent_id":"37"},{"name":"宣武区","id":"406","parent_id":"37"},{"name":"朝阳区","id":"407","parent_id":"37"},{"name":"丰台区","id":"408","parent_id":"37"},{"name":"石景山区","id":"409","parent_id":"37"},{"name":"海淀区","id":"410","parent_id":"37"},{"name":"门头沟区","id":"411","parent_id":"37"},{"name":"房山区","id":"412","parent_id":"37"},{"name":"通州区","id":"413","parent_id":"37"},{"name":"顺义区","id":"414","parent_id":"37"},{"name":"昌平区","id":"415","parent_id":"37"},{"name":"大兴区","id":"416","parent_id":"37"},{"name":"怀柔区","id":"417","parent_id":"37"},{"name":"平谷区","id":"418","parent_id":"37"},{"name":"密云县","id":"419","parent_id":"37"},{"name":"延庆县","id":"420","parent_id":"37"},{"name":"其它区","id":"421","parent_id":"37"}]
         */

        private String name;
        private String id;
        private String parent_id;
        private List<DListBean> d_list;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public List<DListBean> getD_list() {
            return d_list;
        }

        public void setD_list(List<DListBean> d_list) {
            this.d_list = d_list;
        }

        public static class DListBean {
            /**
             * name : 东城区
             * id : 403
             * parent_id : 37
             */

            private String name;
            private String id;
            private String parent_id;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getParent_id() {
                return parent_id;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
            }
        }
    }
}
