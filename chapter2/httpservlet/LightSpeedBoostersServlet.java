package chapter2.httpservlet;

@WebServlet("/api/getLSBData")
public class LightSpeedBoosterServlet extends HttpServlet {

    private LsbRepository lsbRepository;

    @Override
    public void init() throws ServletException {
        lsbRepository = new LsbRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
		// Get all LightSpeedBooster from the datastore
        List<LightSpeedBooster> lightSpeedBooster = lsbRepository.findAll();

        // Convert the lightSpeedBooster to JSON
        String json = new ObjectMapper().writeValueAsString(lightSpeedBooster);

        // Set the response content type and write the JSON response
        response.setContentType("application/json");
        response.getWriter().println(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the lightSpeedBooster data from the request body
        LightSpeedBooster lightSpeedBooster = new ObjectMapper().readValue(request.getInputStream(), LightSpeedBooster.class);

        // Create the user in the database
        lsbRepository.save(lightSpeedBooster);

        // Set the response status to 201 Created
        response.setStatus(HttpServletResponse.SC_CREATED);
    }
}