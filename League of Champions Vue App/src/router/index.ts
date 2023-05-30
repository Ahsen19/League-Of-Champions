import Vue from 'vue'
import VueRouter, { RouteConfig } from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

//array of routes to go beteen different pages
const routes: Array<RouteConfig> = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/squad/:id', 
    name: "SquadWebsite",
    component: () => import('../components/Squad.vue')
  }
]

//giving this VueROuter the array of routes (created above) so it can control all the routes
const router = new VueRouter({
  routes
})

export default router