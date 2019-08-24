<template>
	<md-field-group class="register_submit">
		<md-field v-model="code" icon="mobile" placeholder="请输入验证码">
			<div slot="rightIcon" @click="getCode" class="getCode red">
				<countdown v-if="counting" :time="60000" @countdownend="countdownend">
				  <template slot-scope="props">{{ +props.seconds || 60 }}秒后获取</template>
				</countdown>
				<span v-else>获取验证码</span>
			</div>
		</md-field>
		<md-field v-model="userName"  icon="username" placeholder="请输入用户名"/>
		<md-field v-model="password" type="password" icon="lock" placeholder="请输入密码"/>
		<md-field v-model="repeatPassword" type="password" icon="lock" placeholder="请再次确认密码"/>

		<div class="register_submit_btn">
			<van-button type="danger" size="large" @click="registerSubmit">确定</van-button>
		</div>
	</md-field-group>
</template>

<script>
import field from '@/components/field/';
import fieldGroup from '@/components/field-group/';
import { authRegisterCaptcha,register } from '@/api/api';
import { Message} from 'element-ui';

export default {
  data() {
    return {
      counting: true,
      code: '',
	  phoneNumber:'',
      password: '',
      repeatPassword: '',
	  userName:''
    };
  },
  methods: {
    registerSubmit() {
      this.phoneNumber=this.$route.params.phoneNumber;
      var registerForm={
          code:this.code,
          mobile:this.phoneNumber,
		  password:this.password,
		  userName:this.userName
	  }
	  if(this.userName==''||this.code==''||this.password==''){
          Message.error('验证码、用户名和密码不能为空');
          return;
	  }
	  if(this.password!=this.repeatPassword){
	      if(this.password!=''&&this.repeatPassword!='')
	          Message.error('两次输入的密码不相同');
	      return;
	  }

	  register(registerForm).then(response=>{
	      if(response.data.data==='success'){
            this.$router.push({
                name: 'registerStatus',
                params: { status: 'success' }
            });
		}else{
            this.$router.push({
                name: 'registerStatus',
                params: { status: 'fail' }
            });
		}
	  })

    },

    getCode() {
      this.counting = true;
      this.phoneNumber=this.$route.params.phoneNumber;
      console.log(this.phoneNumber)
      authRegisterCaptcha({mobile:this.phoneNumber}).then(response=>{
          this.$message({
          message: '验证码已发送请注意查收',
          type: 'success'
      });
	  }).catch(response => {
            this.$notify.error({
            title: '失败',
            message: response.data.errmsg
        })
    })
    },
    countdownend() {
      this.counting = false;
    }
  },

  components: {
    [field.name]: field,
    [fieldGroup.name]: fieldGroup
  }
};
</script>


<style lang="scss" scoped>
@import '../../../assets/scss/mixin';

.register_submit {
  padding-top: 40px;
  background-color: #fff;
}

.register_submit_btn {
  padding-top: 30px;
}

.getCode {
  @include one-border(left);
  text-align: center;
}

.time_down {
  color: $red;
}
</style>
