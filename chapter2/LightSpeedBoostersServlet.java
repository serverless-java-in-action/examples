@WebServlet("/api/getLSBData")
public class LightSpeedBoostersServlet extends HttpServlet {

    private LsbRepository lsbRepository;

    @Override
    public void init() throws ServletException {
        lsbRepository = new LsbRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
		// Get all LightSpeedBoosters from the datastore
        List<LightSpeedBoosters> lightSpeedBoosters = lsbRepository.findAll();

        // Convert the lightSpeedBoosters to JSON
        String json = new ObjectMapper().writeValueAsString(lightSpeedBoosters);

        // Set the response content type and write the JSON response
        response.setContentType("application/json");
        response.getWriter().println(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the lightSpeedBoosters data from the request body
        LightSpeedBoosters lightSpeedBoosters = new ObjectMapper().readValue(request.getInputStream(), LightSpeedBoosters.class);

        // Create the user in the database
        lsbRepository.save(lightSpeedBoosters);

        // Set the response status to 201 Created
        response.setStatus(HttpServletResponse.SC_CREATED);
    }
}