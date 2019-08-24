<template>
	<md-field-group class="register_view">
		<div>我们将发送验证码到您的手机</div>
		<md-field
			v-model="mobile"
			icon="mobile"
			placeholder="请输入手机号"/>

		<div class="register_submit">
			<van-button size="large" type="danger" @click="submitCode">下一步</van-button>
		</div>

		<div class="register_footer">
			已有账号?
			<router-link to="/login" class="red">登录</router-link>
		</div>
	</md-field-group>
</template>

<script>
import field from '@/components/field/';
import fieldGroup from '@/components/field-group/';
import {authRegisterCaptcha} from '@/api/api'

export default {
  data() {
    return {
      mobile: ''
    };
  },

  methods: {
    submitCode() {
        authRegisterCaptcha({mobile:this.mobile}).then(response=>{
            this.$message({
            message: '验证码已发送请注意查收',
            type: 'success'
        });
            this.$router.push({
            name: 'registerSubmit',
            params: { phoneNumber: this.mobile }
        });
		}).catch(response => {
            this.$notify.error({
            title: '失败',
            message: response.data.errmsg
        })
    })

    }
  },

  components: {
    [field.name]: field,
    [fieldGroup.name]: fieldGroup
  }
};
</script>

<style lang="scss" scoped>
div.register_view {
  background-color: #fff;
  padding-top: 30px;
}

div.register_submit {
  padding-top: 30px;
  padding-bottom: 20px;
}

.register_footer {
  text-align: right;
  color: $font-color-gray;
}
</style>
