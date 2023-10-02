package chapter2.springboot;

@RestController
@RequestMapping("/api")
public class LightSpeedBoosterController {

	private final LBSService lbsService;

	public LightSpeedBoosterController(LBSService lbsService) {
		this.lbsService = lbsService;
	}

    @GetMapping("/getLSBData")
    @Operation(summary = "Get all LightSpeedBooster data", description = "Get all LightSpeedBooster data")
	@ApiResponse(responseCode = "200", description = "All LightSpeedBooster data")
	public Collection<LightSpeedBooster> list() {
		return this.lbsService.getLightSpeedBooster();
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Add a new LightSpeedBooster", description = "Add a new LightSpeedBooster")
	@ApiResponse(responseCode = "200", description = "LightSpeedBooster added")
	public Collection<LightSpeedBooster> add(@Parameter(required = true, description = "LightSpeedBooster to add") @Valid @RequestBody LightSpeedBooster lightSpeedBooster) {
		return this.lbsService.addLightSpeedBooster(lightSpeedBooster);
	}
    
}
