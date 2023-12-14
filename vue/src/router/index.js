import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import login from '@/view/admin/login'
import main from '@/view/admin/main'
import vip from '@/view/admin/vip/index'
import employee from '@/view/admin/employee/index'
import equipment from '@/view/admin/equipment/index'
import course from '@/view/admin/course/index'
import vipLogin from '@/view/user/login'
import vipMain from '@/view/user/main'
import apply from '@/view/user/apply/index'
import myCourse from '@/view/user/course/index'
import personal from '@/view/user/personal/index'

Vue.use(Router)


export default new Router({
  routes: [
    {
      path: '/',
      name: 'login',
      component: login
    },
    {
      path: '/main',
      name: 'main',
      component: main,
      children:[
        {
          path: '/vip',
          name: 'vip',
          component: vip
        },
        {
          path: '/employee',
          name: 'employee',
          component: employee
        },
        {
          path: '/equipment',
          name: 'equipment',
          component: equipment
        },
        {
          path: '/course',
          name: 'course',
          component: course
        },
    ]
    },
    {
      path: '/vipLogin',
      name: 'vipLogin',
      component: vipLogin
    },
    {
      path: '/vipMain',
      name: 'vipMain',
      component: vipMain,
      children:[
        {
          path: '/apply',
          name: 'apply',
          component: apply
        },
        {
          path: '/myCourse',
          name: 'myCourse',
          component: myCourse
        },
        {
          path: '/personal',
          name: 'personal',
          component: personal
        },

      ]
    },

  ]
})
