

import play.GlobalSettings;
import play.libs.F.Promise;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.SimpleResult;
import play.mvc.Http.Context;

public class Global extends GlobalSettings {
	
//	private class ActionWrapper extends Action.Simple {
//        public ActionWrapper(Action<?> action) {
//            this.delegate = action;
//        }
//
//        @Override
//        public Promise<SimpleResult> call(Http.Context ctx) throws java.lang.Throwable {
//            Promise<SimpleResult> result = this.delegate.call(ctx);
//            Http.Response response = ctx.response();
//            response.setHeader("Access-Control-Allow-Origin", "*");
//            return result;
//        }
//    }

    @Override
    public Action<?> onRequest(Http.Request request, java.lang.reflect.Method actionMethod) {
    	System.out.println("This is overriden: " + request.toString());
//    	Http.Response response = 
//    	response.setHeader(Http.Response.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
//        return super.onRequest(request, actionMethod);
    	return new Action.Simple() {
			
			@Override
			public Promise<SimpleResult> call(Context ctx) throws Throwable {
				Http.Response response = ctx.response();
				response.setHeader(Http.Response.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
				return delegate.call(ctx);
			}
		};
    }

}
