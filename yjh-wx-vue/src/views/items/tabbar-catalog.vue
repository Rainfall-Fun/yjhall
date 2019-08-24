<template>
  <div class="tab_class">
    <div class="tal_class_searchBox">
      <van-search placeholder="点击前往搜索"/>
      <div class="tal_class_searchMask" @click="$router.push({ name: 'search' })"></div>
    </div>
    <div class="class_tree clearfix">
      <ul class="class_tree_nav">
      <li
        v-for="(item, index) in categoryList"
        :key="index"
        :class="{active_nav: currentCategory.id == item.spflbm}"
        @click="changeCatalog(item.spflbm)"
      >{{item.spfl}}</li>
    </ul>
    <div class="class_tree_content">
      <div class="class_tree_all">
        <img style="width:250px" src="http://yanxuan.nosdn.127.net/e8bf0cf08cf7eda21606ab191762e35c.png">
      </div>
      <div class="box">
        <!--<span>{{currentCategory.bz}}</span>-->
        <span>very nice</span>
      </div>
      <div class="class_tree_items_wrap clearfix">
        <!--<el-card class="box-card">-->
          <!--<div slot="header" class="clearfix">-->
            <!--<span>卡片名称</span>-->
          <!--</div>-->
          <!--<el-row>-->
            <!--<el-col :span="4">-->
              <!--<div @click="toItemList(item.spflbm)" :key="i" v-for="(item, i) in currentSubCategoryList" class="text item">-->
                <!--<div class="class_tree_item_img">-->
                  <!--&lt;!&ndash;<img :src="item.picUrl" :alt="item.spfl">&ndash;&gt;-->
                  <!--<img src="http://localhost:8777/img/yjh/f64333d6-f996-43e2-91c2-28b2a953355chot3.png" :alt="item.spfl">-->
                <!--</div>-->
                <!--<div class="class_tree_item_name">{{item.spfl}}</div>-->
              <!--</div>-->
            <!--</el-col>-->
          <!--</el-row>-->
        <!--</el-card>-->
        <div @click="toItemList(item.spflbm)" :key="i" v-for="(item, i) in currentSubCategoryList">
          <div class="class_tree_item_img">
            <!--<img :src="item.picUrl" :alt="item.spfl">-->
            <img src="http://localhost:8777/img/yjh/f64333d6-f996-43e2-91c2-28b2a953355chot3.png" :alt="item.spfl">
          </div>
          <div class="class_tree_item_name">{{item.spfl}}</div>
        </div>
      </div>
    </div>
  </div>

  </div>
</template>

<script>
import { catalogList, catalogCurrent } from '@/api/api';

import { Search } from 'vant';
import { Tree} from 'element-ui';

export default {
  data() {
    return {
      categoryList: [],
      currentCategory: {},
      currentSubCategoryList: [],

    };
  },

  created() {
    this.initData();
  },

  methods: {
    initData() {
      catalogList().then(res => {
        let data = res.data.data;
        this.categoryList = data.categoryList;
        this.currentCategory = res.data.data.currentCategory;
        this.currentSubCategoryList = data.currentSubCategory;
        console.log(this.currentSubCategoryList)
      });
    },
    changeCatalog(id) {
      catalogCurrent({ id: id}).then(res => {
        let data = res.data.data;
        this.currentCategory = data.currentCategory;        
        this.currentSubCategoryList = data.currentSubCategory;
      });
    },
    toItemList(id) {
      this.$router.push({
        name: 'category',
        query: { keyword: '', itemClass: id }
      });
    }
  },
  components: {
    [Search.name]: Search
  }
};
</script>


<style lang="scss" scoped>
@import '../../assets/scss/mixin';

.tab_class {
  overflow: hidden;
  background-color: #fff;
}

.height-fix {
  padding-bottom: 42px;
}

.tal_class_searchBox {
  position: relative;
}

.tal_class_searchMask {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 9;
}


.box {
  width: 250px;
  height: 20px;
  text-align: center;
  font-family: PingFangSC-Light, helvetica, 'Heiti SC';
  font-size: 13px;
  position: absolute;
  top: 95px;
}
.box span {
  line-height: 20px;
}
.class_tree {
  position: relative;
  background-color: #fff;
  overflow-x: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
  height: 100%;
  box-sizing: border-box;
}
.class_tree_nav {
  float: left;
  width: 100px;
  height: 100%;
  background-color: #fff;
  overflow: scroll;
  > li {
    @include one-border;
    height: 40px;
    line-height: 40px;
    text-align: center;
    border-left: 2px solid $bg-color;
  }
  > li.active_nav {
    background-color: #fff;
    border-left: 2px solid $red;
    color: $red;
  }  
}
.class_tree_content {
  margin-left: 100px;
  height: 100%;
  overflow-x: hidden;
  overflow-y: scroll;
  .class_tree_all {
    text-align: right;
    padding-right: 10px;
    height: 40px;
    line-height: 40px;
    color: $font-color-gray;
    font-size: $font-size-small;
  }
  .van-icon-arrow {
    font-size: $font-size-small;
  }
  .class_tree_items_wrap {
    padding: 10px 20px;
    margin-right: -3%;
    margin-top: 70px;
    text-align: center;
    > div {
      float: left;
      padding-right: 3%;
      box-sizing: border-box;
      width: 33.333%;
      margin-bottom: 20px;
    }
    img {
      max-width: 100%;
    }

    .class_tree_item_img {
      display: inline-block;
      max-width: 100%;
      width: 70px;
      height: 70px;
    }
    .class_tree_item_name {
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
    }
  }
}


</style>
