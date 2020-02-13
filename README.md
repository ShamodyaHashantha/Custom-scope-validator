# Custom-scope-validator 
This is a custom scope validator which can be used as a extention through the scope validation process in WSO2 Identity Server.
In this solution, it has introduced a scope configuration as a separate scope configuration file. Once the allowed scopes are configured in there, every implicit authentication requests which are coming with scope parameters are validating against the predefined scopes in that scope configuration file. As per the validation process, it permits scopes to proceed on the token request and drop the undefined scopes in the implicit request.

Thus due to this approach tokens are issued only to the allowed scopes.


This component has been tested with WSO2 IS 5.9.0 and can apply all the previous WSO2 IS versions

# Step to apply the custom scope validator

 - Clone and exrtact the repo
 - Configure your allowed scopes as shown below in scope configuration property file and save it with ".properties" Extention then place it inside your WSO2 IS pack. (Since most of the configuration files are located in "**<IS_HOME>/repository/conf**" directory as a by default, you can put it there as well.)
  
    ```java
    scope_management = scope1,scope2,scope3,scope4
    ```
- You can configure the scopes in existing config file in IS pack by changing the sample source code accordingly.
- Define and initialize the ```scope_management_properties``` variable and ```scope_properties``` variable(According to your scope configuration property file.) in "OAuthScopeValidatorExtention" class where you can find in extracted sample custom validator source code
   ```java
    private static final String scope_properties = "scope_management";
	public static final String scope_management_properties = "scopeConf.properties";
   ```
	 
- Build the component by running ```mvn clean install```
- Obtain the build JAR file from the "**<SOURCE_CODE>/target**" directory and put it into"**<IS_HOME>/repository/component/dropins**" directory.
- Configure the custom scope validator in the "**deployment.toml**" file which can be found in "**<IS_HOME>/repository/conf**" directory shown as below.
	```
    [[oauth.custom_scope_validator]]
	class = "full qualified class name of custom scope validator class"
	.......................................................................................
	[[oauth.custom_scope_validator]]
	class = "org.wso2.custom.carbon.identity.oauth2.validators.OAuthScopeValidatorExtention"
  ```
- Start the pack.
- Create and configure the service provider and enable the custom scope validator for that particular service provider.
- Once you enable the scope validator you are able to permit scopes in implicit authentication request and remove the unnecessary scopes from the request.
