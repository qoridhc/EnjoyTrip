import { createApp } from "vue";
import { createPinia } from "pinia";
import piniaPluginPersistedstate from "pinia-plugin-persistedstate";

import App from "./App.vue";
import router from "./router";

import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap";
import { library } from "@fortawesome/fontawesome-svg-core";
import { faPlane, faPassport, faBolt, faClock } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";

const app = createApp(App);
const pinia = createPinia();

pinia.use(piniaPluginPersistedstate);
library.add(faPlane, faPassport, faBolt, faClock);

app.use(pinia);

app.component("font-awesome-icon", FontAwesomeIcon);
// app.use(createPinia());
app.use(router);

app.mount("#app");
