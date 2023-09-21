package chapter2;

@RestController
@RequestMapping("/api")
public class LightSpeedBoostersController {

	private final LBSService lbsService;

	public LightSpeedBoostersController(LBSService lbsService) {
		this.lbsService = lbsService;
	}

    @GetMapping("/getLSBData")
    @Operation(summary = "Get all LightSpeedBoosters", description = "Get all LightSpeedBoosters")
	@ApiResponse(responseCode = "200", description = "All LightSpeedBoosters")
	public Collection<LightSpeedBoosters> list() {
		return this.lbsService.getLightSpeedBoosters();
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Add a new LightSpeedBooster", description = "Add a new LightSpeedBooster")
	@ApiResponse(responseCode = "200", description = "LightSpeedBooster added")
	public Collection<LightSpeedBooster> add(@Parameter(required = true, description = "LightSpeedBooster to add") @Valid @RequestBody LightSpeedBooster lightSpeedBooster) {
		return this.lbsService.addLightSpeedBoostet(lightSpeedBooster);
	}
    
}
