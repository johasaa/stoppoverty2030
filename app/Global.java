

import play.GlobalSettings;
import play.mvc.Action;
import play.mvc.Http;

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
    	Http.Response response = Http.Context.current().response();
    	response.setHeader("Access-Control-Allow-Origin", "*");
        return super.onRequest(request, actionMethod);
    }

}
