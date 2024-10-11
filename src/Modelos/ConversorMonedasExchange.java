package Modelos;

import com.google.gson.annotations.SerializedName;

public record ConversorMonedasExchange
        (@SerializedName("base_code") String base_code,
         @SerializedName("target_code") String target_code,
         @SerializedName("conversion_rate") double conversion_rate,
         @SerializedName("conversion_result") double conversion_result)
{

}
