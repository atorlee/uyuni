/* global onDocumentReadyInitOldJS */
import App, {HtmlScreen} from "senna";
import "senna/build/senna.css"
import "./spa-engine.css"
import SpaRenderer from "core/spa/spa-renderer";

window.pageRenderers = window.pageRenderers || {};
window.pageRenderers.spa = window.pageRenderers.spa || {};

window.pageRenderers.spa.init = function init() {
  const appInstance = new App();
  appInstance.setLinkSelector("a.js-spa");
  appInstance.setFormSelector("");
  appInstance.addSurfaces('left-menu-data');
  appInstance.addSurfaces('ssm-box');
  appInstance.addSurfaces('page-body');
  appInstance.addRoutes([{
    path: /.*/,
    handler: function (route, a, b) {
      const screen = new HtmlScreen();
      screen.setCacheable(false);
      return screen;
    }
  }]);

  appInstance.on('endNavigate', function(navigation) {
    // workaround to redirect to the login page when there is no session:
    // More info: https://github.com/liferay/senna.js/issues/302
    const urlPath = appInstance.browserPathBeforeNavigate;
    if(["Login.do", "/manager/login"].some(loginPath => urlPath && urlPath.includes(loginPath))) {
      document.getElementsByClassName("spacewalk-main-column-layout")[0].innerHTML = `
        <div class="container-fluid">
            <div class="alert alert-danger">
                No session. Redirecting to login page...
            </div>
        </div>
      `;
      window.location = urlPath;
    }

    // If an error happens we make a full refresh to make sure the original request is shown instead of a SPA replacement
    if (navigation.error && navigation.error.invalidStatus) {
      window.location = navigation.path;
    }

    SpaRenderer.onSpaEndNavigation();
    onDocumentReadyInitOldJS();
  });

  return appInstance;
}