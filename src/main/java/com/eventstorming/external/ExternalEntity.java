





















































forEach: Relation
fileName: {{target.aggregate.namePascalCase}}.java
path: {{boundedContext.name}}/{{{options.packagePath}}}/external
except: {{contexts.except}}
---
package {{options.package}}.external;

import lombok.Data;
import java.util.Date;
{{#target.aggregate}}
@Data
public class {{namePascalCase}} {

    {{#aggregateRoot.fieldDescriptors}}
    private {{safeTypeOf className}} {{nameCamelCase}};
    {{/aggregateRoot.fieldDescriptors}}
}
{{/target.aggregate}}



<function>
    let isGetInvocation = ((this.source._type.endsWith("Command") || this.source._type.endsWith("Policy")) && (this.target._type.endsWith("View") || this.target._type.endsWith("Aggregate")))
    let isPostInvcation = ((this.source._type.endsWith("Event") || this.source._type.endsWith("Policy")) && this.target._type.endsWith("Command"))
    let isExternalInvocation = (this.source.boundedContext.name != this.target.boundedContext.name)

    this.contexts.except = !(isExternalInvocation && (isGetInvocation || isPostInvocation))

    window.$HandleBars.registerHelper('safeTypeOf', function (className) {
        if(className.endsWith("String") || className.endsWith("Integer") || className.endsWith("Long") || className.endsWith("Double") || className.endsWith("Float")
            || className.endsWith("Boolean") || className.endsWith("Date")){
            return className;
        }else
            return "Object";
        // if(className.indexOf("List")==0){
        //     return "java.util.List<java.util.Map>";
        // } else{
        //     return "java.util.Map";
        // } 
        //else if (enum) return "String"
    })


//window.$HandleBars.registerHelper('checkDateType', function (fieldDescriptors) {
//for(var i = 0; i < fieldDescriptors.length; i ++ ){
//    if(fieldDescriptors[i] && fieldDescriptors[i].className == 'Date'){
//        return "import java.util.Date; \n"
//        }
//    }
//});
</function>