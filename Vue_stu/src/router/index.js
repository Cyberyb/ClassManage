import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/Manage.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Manage',
    redirect: '/home',
    component: () => import('../views/Manage.vue'),
    children: [
      {
        path: '/home',
        name:'Home',
        component: () => import('../views/Home')
      },
      {
        path: 'student',
        name: 'Student',
        component: () => import('../views/Student.vue'),
      },
      {
        path: 'course',
        name: 'course',
        component: () => import('../views/Course.vue'),
      },
      {
        path: 'department',
        name: 'department',
        component: () => import('../views/Department.vue'),
      },
      {
        path: 'teacher',
        name: 'teacher',
        component: () => import('../views/Teacher.vue'),
      },
      {
        path: 'openclass',
        name: 'openclass',
        component: () => import('../views/Openclass.vue'),
      },
      {
        path: 'electclass',
        name: 'electclass',
        component: () => import('../views/Electclass.vue'),
      },

    ]
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
