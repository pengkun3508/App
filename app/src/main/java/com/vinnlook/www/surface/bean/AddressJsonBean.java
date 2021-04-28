package com.vinnlook.www.surface.bean;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

/**
 * @Description:
 * @Time:2021/3/19$
 * @Author:pk$
 */
public class AddressJsonBean implements IPickerViewData {

    @Override
    public String getPickerViewText() {
        return name;
    }
    /**
     * id : 2
     * parent_id : 1
     * name : 北京
     * list : [{"id":"37","parent_id":"2","name":"北京市","list":[{"id":"403","parent_id":"37","name":"东城区"},{"id":"404","parent_id":"37","name":"西城区"},{"id":"405","parent_id":"37","name":"崇文区"},{"id":"406","parent_id":"37","name":"宣武区"},{"id":"407","parent_id":"37","name":"朝阳区"},{"id":"408","parent_id":"37","name":"丰台区"},{"id":"409","parent_id":"37","name":"石景山区"},{"id":"410","parent_id":"37","name":"海淀区"},{"id":"411","parent_id":"37","name":"门头沟区"},{"id":"412","parent_id":"37","name":"房山区"},{"id":"413","parent_id":"37","name":"通州区"},{"id":"414","parent_id":"37","name":"顺义区"},{"id":"415","parent_id":"37","name":"昌平区"},{"id":"416","parent_id":"37","name":"大兴区"},{"id":"417","parent_id":"37","name":"怀柔区"},{"id":"418","parent_id":"37","name":"平谷区"},{"id":"419","parent_id":"37","name":"密云县"},{"id":"420","parent_id":"37","name":"延庆县"},{"id":"421","parent_id":"37","name":"其它区"}]}]
     */

    private String id;
    private String parent_id;
    private String name;
    private List<ListBeanX> list;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ListBeanX> getList() {
        return list;
    }

    public void setList(List<ListBeanX> list) {
        this.list = list;
    }

    public static class ListBeanX {
        /**
         * id : 37
         * parent_id : 2
         * name : 北京市
         * list : [{"id":"403","parent_id":"37","name":"东城区"},{"id":"404","parent_id":"37","name":"西城区"},{"id":"405","parent_id":"37","name":"崇文区"},{"id":"406","parent_id":"37","name":"宣武区"},{"id":"407","parent_id":"37","name":"朝阳区"},{"id":"408","parent_id":"37","name":"丰台区"},{"id":"409","parent_id":"37","name":"石景山区"},{"id":"410","parent_id":"37","name":"海淀区"},{"id":"411","parent_id":"37","name":"门头沟区"},{"id":"412","parent_id":"37","name":"房山区"},{"id":"413","parent_id":"37","name":"通州区"},{"id":"414","parent_id":"37","name":"顺义区"},{"id":"415","parent_id":"37","name":"昌平区"},{"id":"416","parent_id":"37","name":"大兴区"},{"id":"417","parent_id":"37","name":"怀柔区"},{"id":"418","parent_id":"37","name":"平谷区"},{"id":"419","parent_id":"37","name":"密云县"},{"id":"420","parent_id":"37","name":"延庆县"},{"id":"421","parent_id":"37","name":"其它区"}]
         */

        private String id;
        private String parent_id;
        private String name;
        private List<ListBean> list;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 403
             * parent_id : 37
             * name : 东城区
             */

            private String id;
            private String parent_id;
            private String name;

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
