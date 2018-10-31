export class ENV {

    public static currentEnvironment: string = "development";
    //public static currentEnvironment: string = "production";

    public static development: any = {
         ApiUrlBase: "http://adoteumpetwebservice-dev.azurewebsites.net/api/"
    };
    public static production: any = {
        ApiUrlBase: "http://adoteumpetwebservice.azurewebsites.net/api/"
    };
}