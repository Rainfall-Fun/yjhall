export default [
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/login/login')
  },
  {
    path: '/login/registerGetCode',
    name: 'registerGetCode',
    component: () => import('@/views/login/register-getCode')
  },
  {
    path: '/login/registerSubmit/:phoneNumber',
    name: 'registerSubmit',
    component: () => import('@/views/login/register-submit')
  },
  {
    path: '/login/registerStatus/:status',
    name: 'registerStatus',
    props: true,
    component: () => import('@/views/login/register-status')
  },
  {
    path: '/login/forget',
    name: 'forget',
    component: () => import('@/views/login/forget')
  },
  {
    path: '/login/forget/reset',
    name: 'forgetReset',
    component: () => import('@/views/login/forget-reset')
  },
  {
    path: '/login/forget/reset/:status',
    name: 'forgetStatus',
    props: true,
    component: () => import('@/views/login/forget-status')
  }
];
