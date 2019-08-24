<template>
  <div class="app-container">

    <el-card class="box-card">
      <h3>商品介绍</h3>
      <el-form ref="goods" :rules="rules" :model="goods" label-width="150px">
        <!--<el-form-item label="商品编号" prop="goodsSn">-->
        <!--<el-input v-model="goods.goodsSn"/>-->
        <!--</el-form-item>-->
        <el-form-item label="商品名称" prop="name">
          <el-input v-model="goods.name"/>
        </el-form-item>
        <el-form-item label="市场价格" prop="counterPrice">
          <el-input  v-model="goods.counterPrice" placeholder="0.00" >
            <template slot="append">元</template>
          </el-input>
        </el-form-item>
        <el-form-item label="当前价格" prop="retailPrice">
          <el-input v-model="goods.retailPrice" placeholder="0.00" >
            <template slot="append">元</template>
          </el-input>
        </el-form-item>
        <el-form-item label="成本价" prop="costPrice">
          <el-input v-model="goods.costPrice" placeholder="0.00">
            <template slot="append">元</template>
          </el-input>
        </el-form-item>
        <el-form-item label="税费" prop="taxCost">
          <el-input v-model="goods.taxCost" placeholder="0.00">
            <template slot="append">元</template>
          </el-input>
        </el-form-item>
        <el-form-item label="是否是推荐商品" prop="isRecommendation">
          <el-radio-group v-model="goods.isRecommendation">
            <el-radio :label="1">是</el-radio>
            <el-radio :label="0">否</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="是否上架" prop="isOnSale">
          <el-radio-group v-model="goods.isOnSale">
            <el-radio :label="1">是</el-radio>
            <el-radio :label="0">否</el-radio>
          </el-radio-group>
        </el-form-item>

        <!--<el-form-item label="商品图片">-->
          <!--<el-upload-->
            <!--:action="uploadPath"-->
            <!--:show-file-list="false"-->
            <!--:headers="headers"-->
            <!--:on-success="uploadPicUrl"-->
            <!--class="avatar-uploader"-->
            <!--accept=".jpg,.jpeg,.png,.gif">-->
            <!--<img v-if="goods.picUrl" :src="goods.picUrl" class="avatar">-->
            <!--<i v-else class="el-icon-plus avatar-uploader-icon"/>-->
          <!--</el-upload>-->
        <!--</el-form-item>-->

        <el-form-item label="宣传画廊">
        <el-upload
        :action="uploadPath"
        :limit="5"
        :headers="headers"
        :on-exceed="uploadOverrun"
        :on-success="handleGalleryUrl"
        :on-remove="handleRemove"
        :file-list="picList"
        multiple
        accept=".jpg,.jpeg,.png,.gif"
        list-type="picture-card">
          <!--<img  :src="image" class="avatar" v-for="(image, index) in goods.gallery">-->
          <i class="el-icon-plus"/>
        </el-upload>
        </el-form-item>

        <el-form-item label="计量单位">
          <el-input v-model="goods.unit" placeholder="件 / 个 / 盒"/>
        </el-form-item>

        <!--<el-form-item label="关键字">-->
        <!--<el-tag v-for="tag in keywords" :key="tag" closable type="primary" @close="handleClose(tag)">-->
        <!--{{ tag }}-->
        <!--</el-tag>-->
        <!--<el-input-->
        <!--v-if="newKeywordVisible"-->
        <!--ref="newKeywordInput"-->
        <!--v-model="newKeyword"-->
        <!--class="input-new-keyword"-->

        <!--@keyup.enter.native="handleInputConfirm"-->
        <!--@blur="handleInputConfirm"/>-->
        <!--<el-button v-else class="button-new-keyword" type="primary" @click="showInput">+ 增加</el-button>-->
        <!--</el-form-item>-->


        <el-form-item label="所属分类">
          <el-cascader
            placeholder="选择所属分类，可搜索"
            :options="categoryList"
            v-model="categoryIds"
            change-on-select
            filterable
            clearable
            expand-trigger="hover"
            @change="handleCategoryChange"/>
        </el-form-item>
        <el-form-item label="所属板块">
          <el-select v-model="goods.belongModule">
            <el-option v-for="item in moduleList" :key="item.value" :label="item.label" :value="item.value"/>
          </el-select>
        </el-form-item>
        <el-form-item label="所属品牌商">
          <el-select v-model="goods.brandId">
            <el-option v-for="item in brandList" :key="item.value" :label="item.label" :value="item.value"/>
          </el-select>
        </el-form-item>
        <el-form-item label="所属供应商">
          <el-select v-model="goods.supplierId">
            <el-option v-for="item in supplierList" :key="item.value" :label="item.label" :value="item.value"/>
          </el-select>
        </el-form-item>
        <el-form-item label="支付方式">
          <el-select v-model="goods.paymentWay">
            <el-option v-for="item in paymentWayList" :key="item.value" :label="item.label" :value="item.value"/>
          </el-select>
        </el-form-item>
        <el-form-item label="运费规则">
          <el-select v-model="goods.freightRule">
            <el-option v-for="item in freightRuleList" :key="item.value" :label="item.label" :value="item.value"/>
          </el-select>
        </el-form-item>

        <el-form-item label="商品详细介绍">
          <editor :init="editorInit" v-model="goods.detail"/>
        </el-form-item>
      </el-form>
    </el-card>

    <el-card class="box-card">
      <h3>商品属性值</h3>
      <!--<el-form ref="goods" :rules="rules" :model="goods" label-width="150px">-->
      <el-form   label-width="150px">
        <el-form-item :label="item.extensionAttributeName" v-for="(item,index) in extensionAttributes">
          <el-input v-model="extensionAttributes[index].extensionAttributeValue"/>
        </el-form-item>
      </el-form>
    </el-card>
    <el-card class="box-card">
      <h3>商品规格</h3>
      <!--<el-button :plain="true" type="primary" @click="handleSpecificationShow">添加</el-button>-->
      <el-row :gutter="20" type="flex" align="middle" style="padding:20px 0;">
        <el-col :span="10">
          <el-radio-group v-model="multipleSpec" @change="specChanged">
            <el-radio-button :label="false">默认标准规格</el-radio-button>
            <el-radio-button :label="true">多规格支持</el-radio-button>
          </el-radio-group>
        </el-col>
        <el-col v-if="multipleSpec" :span="10">
          <el-button :plain="true" type="primary" @click="handleSpecificationShow">添加</el-button>
        </el-col>
      </el-row>
      <el-table :data="specifications">
        <el-table-column property="specification" label="规格名" />
        <el-table-column property="value" label="规格值" >
          <template slot-scope="scope">
            <el-tag type="primary">
              {{ scope.row.value }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column align="center" label="操作" width="250" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button type="danger" size="mini" @click="handleSpecificationDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <el-dialog :visible.sync="specVisiable" title="设置规格">
        <el-form ref="specForm" :rules="rules" :model="specForm" status-icon label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
          <el-form-item label="规格名" prop="specification">
            <el-input v-model="specForm.specification"/>
          </el-form-item>
          <el-form-item label="规格值" prop="value">
            <el-input v-model="specForm.value"/>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="specVisiable = false">取消</el-button>
          <el-button type="primary" @click="handleSpecificationAdd">确定</el-button>
        </div>
      </el-dialog>
    </el-card>

    <el-card class="box-card">
      <h3>商品库存</h3>
      <el-table :data="products">
        <el-table-column property="value" label="货品规格">
        </el-table-column>
        <el-table-column property="price" width="100" label="货品售价"/>
        <el-table-column property="costPrice" width="100" label="货品成本"/>
        <el-table-column property="number" width="100" label="货品数量"/>
        <el-table-column property="biggestDeductionAmount" width="100" label="积分最大抵扣额度"/>
        <el-table-column property="url" width="100" label="货品图片">
          <template slot-scope="scope">
            <img v-if="scope.row.url" :src="scope.row.url" width="40">
          </template>
        </el-table-column>
        <el-table-column align="center" label="操作" width="100" property="setting" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button type="primary" size="mini" @click="handleProductShow(scope.row)">设置</el-button>
          </template>
        </el-table-column>
      </el-table>

      <el-dialog :visible.sync="productVisiable" title="设置货品">
        <el-form
          ref="productForm"
          :model="productForm"
          status-icon
          label-position="left"
          label-width="100px"
          style="width: 400px; margin-left:50px;" :rules="rules">
          <el-form-item label="货品售价" prop="price">
            <el-input  v-model="productForm.price"/>
          </el-form-item>
          <el-form-item label="货品成本价" prop="costPrice">
            <el-input  v-model="productForm.costPrice"/>
          </el-form-item>
          <el-form-item label="货品数量" prop="number">
            <el-input v-model="productForm.number"/>
          </el-form-item>
          <el-form-item label="积分最大抵扣额度" prop="biggestDeductionAmount">
            <el-input v-model="productForm.biggestDeductionAmount"/>
          </el-form-item>
          <el-form-item label="货品图片" prop="url">
            <el-upload
              :action="uploadPath"
              :show-file-list="false"
              :headers="headers"
              :on-success="uploadProductUrl"
              class="avatar-uploader"
              accept=".jpg,.jpeg,.png,.gif">
              <img v-if="productForm.url" :src="productForm.url" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"/>
            </el-upload>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="productVisiable = false">取消</el-button>
          <el-button type="primary" @click="handleProductEdit()">确定</el-button>
        </div>
      </el-dialog>
    </el-card>


    <div class="op-container">
      <el-button @click="handleCancel">取消</el-button>
      <el-button type="primary" @click="handleEdit">更新商品</el-button>
    </div>

  </div>
</template>

<style>
.el-card {
  margin-bottom: 10px;
}
.el-tag + .el-tag {
  margin-left: 10px;
}
.input-new-keyword {
  width: 90px;
  margin-left: 10px;
  vertical-align: bottom;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #20a0ff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 120px;
  height: 120px;
  line-height: 120px;
  text-align: center;
}
.avatar {
  width: 145px;
  height: 145px;
  display: block;
}
</style>

<script>
import { getGoodsAttributeValue,getGoodsAttribute,detailGoods, editGoods, listCatAndBrandAndSupplier } from '@/api/goods'
import { createStorage, uploadPath } from '@/api/storage'
import Editor from '@tinymce/tinymce-vue'
import { MessageBox } from 'element-ui'
import { getToken } from '@/utils/auth'

export default {
  name: 'GoodsEdit',
  components: { Editor },
  data() {
    return {
      uploadPath,
      newKeywordVisible: false,
      newKeyword: '',
      keywords: [],
      galleryFileList: [],
      categoryList: [],
      brandList: [],
      moduleList:[],
      supplierList:[],
      paymentWayList:[],
      picList:[],
      freightRuleList:[],
      extensionAttributes:[],
      categoryIds: [],
      goods: { gallery: [] },
      specVisiable: false,
      specForm: { specification: '', value: '' },
      multipleSpec: false,
      specifications: [{ specification: '规格', value: '标准' }],
      productVisiable: false,
      productForm: {id: 0, specifications: [], price: 0.0, number: 0,biggestDeductionAmount:0.00, url: ''},
      products: [{ value: '标准', price: 0.00,costPrice:0.00,number:0,biggestDeductionAmount:0.00,  url: '' }],
      attributeVisiable: false,
      attributeForm: { attribute: '', value: '' },
      attributes: [],
      rules: {
        // goodsSn: [
        //   { required: true, message: '商品编号不能为空', trigger: 'blur' }
        // ],
        name: [{ required: true, message: '商品名称不能为空', trigger: 'blur' }],
        counterPrice:[{ required: true, message: '请填写正确的金额,保留两位小数',pattern: /(^[\d]|^[1-9][\d]*)($|[\.][\d]{0,2}$)/}],
        retailPrice:[{ required: true, message: '请填写正确的金额,保留两位小数',pattern: /(^[\d]|^[1-9][\d]*)($|[\.][\d]{0,2}$)/}],
        costPrice:[{ required: true, message: '请填写正确的金额,保留两位小数',pattern: /(^[\d]|^[1-9][\d]*)($|[\.][\d]{0,2}$)/}],
        taxCost:[{ required: true, message: '请填写正确的金额,保留两位小数',pattern: /(^[\d]|^[1-9][\d]*)($|[\.][\d]{0,2}$)/}],
        number:[{required: true, message: '输入的必须为整数',pattern:/^(0|[1-9][0-9]*)$/}],
        price:[{ required: true, message: '请填写正确的金额,保留两位小数',pattern: /(^[\d]|^[1-9][\d]*)($|[\.][\d]{0,2}$)/}],
        biggestDeductionAmount:[{ required: true, message: '请填写正确的金额,保留两位小数',pattern: /(^[\d]|^[1-9][\d]*)($|[\.][\d]{0,2}$)/}]
      },
      editorInit: {
        language: 'zh_CN',
        convert_urls: false,
        plugins: [
          'advlist anchor autolink autosave code codesample colorpicker colorpicker contextmenu directionality emoticons fullscreen hr image imagetools importcss insertdatetime link lists media nonbreaking noneditable pagebreak paste preview print save searchreplace spellchecker tabfocus table template textcolor textpattern visualblocks visualchars wordcount'
        ],
        toolbar: [
          'searchreplace bold italic underline strikethrough alignleft aligncenter alignright outdent indent  blockquote undo redo removeformat subscript superscript code codesample',
          'hr bullist numlist link image charmap preview anchor pagebreak insertdatetime media table emoticons forecolor backcolor fullscreen'
        ],
        images_upload_handler: function(blobInfo, success, failure) {
          const formData = new FormData()
          formData.append('file', blobInfo.blob())
          createStorage(formData)
            .then(res => {
              success(res.data.data.data)
            })
            .catch(() => {
              failure('上传失败，请重新上传')
            })
        }
      }
    }
  },
  computed: {
    headers() {
      return {
        'X-Litemall-Admin-Token': getToken()
      }
    }
  },
  created() {
    this.init()
  },
  methods: {
    init: function() {
      if (this.$route.query.id == null) {
        return
      }
      listCatAndBrandAndSupplier().then(response => {
        this.categoryList = response.data.data.categoryList
        this.brandList = response.data.data.brandList
        this.supplierList=response.data.data.supplierList
        this.moduleList=response.data.data.moduleList
        this.paymentWayList=response.data.data.paymentWayList
        this.freightRuleList=response.data.data.freightRuleList

    })
      const goodsId = this.$route.query.id
      detailGoods(goodsId).then(response => {
        this.goods = response.data.data.goods
        for (var i=0;i<this.goods.gallery.length;i++){
          this.picList.push({url:this.goods.gallery[i]})
        }
        var category=this.goods.categoryId
        var split = category.split(",");
        switch (split.length) {
          case 1:
            this.categoryIds[0]=Number(split[0])
            getGoodsAttribute(this.categoryIds[0]).then(response=>{
              this.extensionAttributes=response.data.data
        })
            break
          case 2:
            this.categoryIds[0]=Number(split[0])
            this.categoryIds[1]=Number(split[1])
      getGoodsAttribute(this.categoryIds[1]).then(response=>{
        this.extensionAttributes=response.data.data
    })
            break
          case 3:
            this.categoryIds[0]=Number(split[0])
            this.categoryIds[1]=Number(split[1])
            this.categoryIds[2]=Number(split[2])
      getGoodsAttribute(this.categoryIds[2]).then(response=>{
        this.extensionAttributes=response.data.data
    })

        }
        this.specifications = response.data.data.specifications
        this.products = response.data.data.products
        if(this.specifications.length==1&&this.specifications[0].specification=='规格'){
          this.multipleSpec=false
        }else
          this.multipleSpec=true

        //获得商品的扩展属性值
        getGoodsAttributeValue(this.goods.goodsId).then(response=>{
           var tempList=response.data.data
           for(var i=0;i<tempList.length;i++){
             for(var j=0;j<this.extensionAttributes.length;j++){
               if(tempList[i].kzsxbm==this.extensionAttributes[j].extensionAttributeId){
                 this.extensionAttributes[j].extensionAttributeValue=tempList[i].kzsxz
                 continue
               }
             }
      }
        })
      })
    },
    handleCategoryChange(value) {
      var result='';
      for(var i=0;i<value.length;i++){
        result=i<value.length-1?result+value[i]+',':result+value[i]
      }
      this.goods.categoryId = result
      getGoodsAttribute(value[value.length-1]).then(response=>{
        this.extensionAttributes=response.data.data
    })
    },
    handleCancel: function() {
      this.$router.push({ path: '/goods/goods' })
    },

    handleEdit: function() {
      const finalGoods = {
        goods: this.goods,
        specifications: this.specifications,
        products: this.products,
        attributes: this.attributes,
        extensionAttributes: this.extensionAttributes
      }
      editGoods(finalGoods)
        .then(response => {
          this.$notify.success({
            title: '成功',
            message: '创建成功'
          })
          this.$router.push({ path: '/goods/list' })
        })
        .catch(response => {
          MessageBox.alert('业务错误：' + response.data.errmsg, '警告', {
            confirmButtonText: '确定',
            type: 'error'
          })
        })
    },
    handleClose(tag) {
      this.keywords.splice(this.keywords.indexOf(tag), 1)
      this.goods.keywords = this.keywords.toString()
    },
    showInput() {
      this.newKeywordVisible = true
      this.$nextTick(_ => {
        this.$refs.newKeywordInput.$refs.input.focus()
      })
    },
    handleInputConfirm() {
      const newKeyword = this.newKeyword
      if (newKeyword) {
        this.keywords.push(newKeyword)
        this.goods.keywords = this.keywords.toString()
      }
      this.newKeywordVisible = false
      this.newKeyword = ''
    },
    uploadPicUrl: function(response) {
      this.goods.picUrl = response.data.data
    },
    uploadOverrun: function() {
      this.$message({
        type: 'error',
        message: '上传文件个数超出限制!最多上传5张图片!'
      })
    },
    handleGalleryUrl(response, file, fileList) {
      if (response.errno === 0) {
        this.goods.gallery.push(response.data.data)
      }
    },
    handleRemove: function(file, fileList) {
      for (var i = 0; i < this.goods.gallery.length; i++) {
        // 这里存在两种情况
        // 1. 如果所删除图片是刚刚上传的图片，那么图片地址是file.response.data.url
        //    此时的file.url虽然存在，但是是本机地址，而不是远程地址。
        // 2. 如果所删除图片是后台返回的已有图片，那么图片地址是file.url
        var url
        if (file.response === undefined) {
          url = file.url
        } else {
          url = file.response.data.url
        }

        if (this.goods.gallery[i] === url) {
          this.goods.gallery.splice(i, 1)
        }
      }
    },
    specChanged: function(label) {
      if (label === false) {
        this.specifications = [{ specification: '规格', value: '标准' }]
        this.products = [{ value: '标准', price: 0.00,costPrice:0.00, number: 0,biggestDeductionAmount:0.00, url: '' }]
      }
      else {
        this.specifications = []
        this.products = []
      }
    },
    uploadSpecPicUrl: function(response) {
      this.specForm.picUrl = response.data
    },
    handleSpecificationShow() {
      this.specForm = { specification: '', value: ''}
      this.specVisiable = true
    },
    handleSpecificationAdd() {
      var specificationSet=new Set();
      var index = this.specifications.length - 1
      for (var i = 0; i < this.specifications.length; i++) {
        const v = this.specifications[i]
        if (v.value === this.specForm.value) {
          this.$message({
            type: 'warning',
            message: '已经存在规格值:' + v.value
          })
          this.specForm = {}
          this.specVisiable = false
          return
        }else {
          index = i
        }
      }
      for(var i=0;i<this.specifications.length;i++)
        specificationSet.add(this.specifications[i].specification);
      specificationSet.add(this.specForm.specification)
      if(specificationSet.size>2){
        this.$message({
          type: 'warning',
          message: '暂只支持两种规格'
        })
        return;
      }
      this.specifications.splice(index + 1, 0, this.specForm)
      this.specVisiable = false
      this.specToProduct()
    },
    handleSpecificationDelete(row) {
      const index = this.specifications.indexOf(row)
      this.specifications.splice(index, 1)
      this.specToProduct()
    },
    specToProduct() {
      if (this.specifications.length === 0) {
        return
      }
      let specificationSet=new Set();//存放规格的set
      let specificationValue=new Set();//存放规格值的set
      var specificationMap=new Map();//存放规格以及对应规格值的map
      for(var i=0;i<this.specifications.length;i++){
        specificationSet.add(this.specifications[i].specification)
        specificationValue.add(this.specifications[i].value)
      }


      var specificationArray=Array.from(specificationSet)
      for(var i=0;i<specificationArray.length;i++){
        specificationMap.set(specificationArray[i],[])
      }
      //将规格和对应的规格值存入map
      for(var i=0;i<specificationArray.length;i++){
        var newVar = specificationMap.get(specificationArray[i]);
        for(var j=0;j<this.specifications.length;j++){
          if(specificationArray[i]==this.specifications[j].specification){
            newVar.push(this.specifications[j].value)
          }
        }
        specificationMap.set(specificationArray[i],newVar)
      }

      var specification1;
      var specification2;
      var specification3;
      switch (specificationArray.length) {
        case 1:
          this.products=[]
          specification1=specificationMap.get(specificationArray[0])
          for(var i=0;i<specification1.length;i++){
            if(this.judge(specification1[i]))
              continue
            var temp={ value: '', price: 0.00,costPrice:0.00,number:0,biggestDeductionAmount:0.00,  url: '' }
            temp.value=specification1[i]
            temp.price=0
            temp.costPrice=0
            temp.number=0
            temp.biggestDeductionAmount=0
            this.products.push(temp)
          }
          break
        case 2:
          this.products=[]
          specification1=specificationMap.get(specificationArray[0])
          specification2=specificationMap.get(specificationArray[1])
          for(var i=0;i<specification1.length;i++){
            for(var j=0;j<specification2.length;j++){
              if(this.judge(specification1[i]+specification2[j]))
                continue
              var temp={ value: '', price: 0.00,costPrice:0.00,number:0,biggestDeductionAmount:0.00,  url: '' }
              temp.value=specification1[i]+","+specification2[j]
              temp.price=0
              temp.costPrice=0
              temp.number=0
              temp.biggestDeductionAmount=0
              this.products.push(temp)
            }
          }
          break
        case 3:
          this.products=[]
          specification1=specificationMap.get(specificationArray[0])
          specification2=specificationMap.get(specificationArray[1])
          specification3=specificationMap.get(specificationArray[2])
          for(var i=0;i<specification1.length;i++){
            for(var j=0;j<specification2.length;j++){
              for(var k=0;k<specification3.length;k++){
                if(this.judge(specification1[i]+specification2[j]+specification3[k]))
                  continue
                var temp={ value: '', price: 0.00,costPrice:0.00,number:0,biggestDeductionAmount:0.00,  url: '' }
                temp.value=specification1[i]+","+specification2[j]+","+specification3[k]
                temp.price=0
                temp.costPrice=0
                temp.number=0
                temp.biggestDeductionAmount=0
                this.products.push(temp)
              }
            }
          }
          break
      }
    },
    judge(value){
      for(var j=0;j<this.products.length;j++)
        if(value==this.products[j].value)
          return true;
        else
          continue
      return false;
    },
    handleProductShow(row) {
      this.productForm = Object.assign({}, row)
      this.productVisiable = true
    },
    uploadProductUrl: function(response) {
      this.productForm.url = response.data.data
    },
    handleProductEdit() {
      for (var i = 0; i < this.products.length; i++) {
        const v = this.products[i]
        if (v.value === this.productForm.value) {
          this.products.splice(i, 1, this.productForm)
          break
        }
      }
      this.productVisiable = false
    },
    handleAttributeShow() {
      this.attributeForm = {}
      this.attributeVisiable = true
    },
    handleAttributeAdd() {
      this.attributes.unshift(this.attributeForm)
      this.attributeVisiable = false
    },
    handleAttributeDelete(row) {
      const index = this.attributes.indexOf(row)
      this.attributes.splice(index, 1)
    }
  }
}
</script>
