# Dependabot Auto-merge Configuration

This repository is configured to automatically merge Dependabot pull requests after successful builds.

## How It Works

1. **Dependabot creates a PR** for dependency updates
2. **Build workflows run** (Java 17 or Java 21 based on project requirements)
3. **Auto-merge workflow activates** after builds complete successfully
4. **PR is automatically approved and merged** for patch and minor updates

## Workflow Files

### `.github/workflows/dependabot-automerge.yml`
Main auto-merge workflow that:
- Waits for all build checks to pass
- Auto-approves and enables auto-merge for patch/minor updates
- Flags major version updates for manual review

### `.github/workflows/build.yml`
Builds all projects with appropriate Java versions:
- **Java 21**: Most Quarkus and Spring Boot projects
- **Java 17**: Azure Functions and specific AWS Lambda projects

### `.github/workflows/dependabot-java21.yml`
Special workflow for Java 21 projects (like knative-springboot) that ensures proper Java version is used during Dependabot builds.

## Auto-merge Rules

### Automatically Merged
- ✅ **Patch updates** (e.g., 1.0.0 → 1.0.1)
- ✅ **Minor updates** (e.g., 1.0.0 → 1.1.0)
- ✅ All build checks pass
- ✅ No conflicts with base branch

### Requires Manual Review
- ⚠️ **Major updates** (e.g., 1.0.0 → 2.0.0)
- ⚠️ Build failures
- ⚠️ Merge conflicts

## Labels

All Dependabot PRs are automatically labeled:
- `dependencies` - All dependency updates
- `java` - Java/Maven dependencies
- `java-21` - Projects requiring Java 21
- `github-actions` - GitHub Actions updates

## Repository Settings Required

For auto-merge to work, ensure these GitHub repository settings are configured:

1. **Allow auto-merge**: Settings → General → Pull Requests → "Allow auto-merge"
2. **Branch protection** (optional but recommended):
   - Require status checks to pass before merging
   - Include build workflows in required checks
3. **GitHub Actions permissions**: Settings → Actions → General → Workflow permissions → "Read and write permissions"

## Troubleshooting

### Auto-merge not working?
1. Check that "Allow auto-merge" is enabled in repository settings
2. Verify GitHub Actions has write permissions
3. Ensure all required status checks are passing
4. Check workflow logs for errors

### Build failures?
- Java 21 projects: Ensure `.java-version` file exists and workflow uses correct Java version
- Check `pom.xml` for compatibility issues
- Review build logs in Actions tab

### Manual intervention needed?
- Major version updates require manual review and approval
- Comment on the PR to trigger re-evaluation if needed
- Use `@dependabot rebase` to rebase the PR

## Monitoring

Monitor Dependabot activity:
- **Insights → Dependency graph → Dependabot**: View all updates
- **Pull requests**: Filter by `author:dependabot[bot]`
- **Actions**: Check workflow runs for build status

## Customization

To modify auto-merge behavior:
1. Edit `.github/workflows/dependabot-automerge.yml`
2. Adjust conditions in the `if` statements
3. Modify `update-type` filters to change which updates auto-merge

## Security

- Only patch and minor updates auto-merge
- Major updates require manual review to assess breaking changes
- All PRs must pass build checks before merging
- Dependabot uses GitHub's security advisories to prioritize updates

---

**Note**: This configuration balances automation with safety, ensuring dependencies stay current while maintaining code quality and stability.