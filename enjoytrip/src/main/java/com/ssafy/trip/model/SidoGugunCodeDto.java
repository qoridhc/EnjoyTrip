package com.ssafy.trip.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Schema(title = "SidoGugunCodeDto : 시도, 구군정보", description = "시도, 구군의 이름을 나타낸다.")
public class SidoGugunCodeDto {

	@Schema(description = "시도코드")
	private String sido_code;
	@Schema(description = "시도이름")
	private String sido_name;
	@Schema(description = "구군코드")
	private String gugun_code;
	@Schema(description = "구군이름")
	private String gugun_name;
	
}
